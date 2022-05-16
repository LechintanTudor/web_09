package ro.ubb.imgur.data;

import org.json.JSONArray;
import org.json.JSONObject;
import ro.ubb.imgur.model.Account;
import ro.ubb.imgur.model.Picture;
import ro.ubb.imgur.persistence.Database;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "pictureData", value = "/api/pictures/data")
public class PictureData extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        Account account = (Account) request.getSession().getAttribute("account");

        try {
            if (account == null) {
                PrintWriter out = new PrintWriter(response.getOutputStream(), true);
                out.println("null");
                return;
            }

            List<Picture> pictures = Database.getAllPictures();
            JSONArray picturesJSON = new JSONArray();

            for (Picture picture : pictures) {
                JSONObject pictureJSON = new JSONObject();
                pictureJSON.put("id", picture.id);
                pictureJSON.put("filePath", picture.filePath);
                pictureJSON.put("accountId", picture.accountId);
                pictureJSON.put("totalVotes", picture.totalVotes);
                picturesJSON.put(pictureJSON);
            }

            PrintWriter out = new PrintWriter(response.getOutputStream(), true);
            out.println(picturesJSON);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        try {
            if (account == null) {
                PrintWriter out = new PrintWriter(response.getOutputStream(), true);
                out.println("null");
                return;
            }

            byte[] bodyBytes = request.getInputStream().readAllBytes();
            String body = new String(bodyBytes);
            JSONObject bodyJSON = new JSONObject(body);
            System.out.println(body);

            long pictureId = bodyJSON.getLong("pictureId");
            long voteValue = bodyJSON.getLong("voteValue");
            Database.submitVote(account.id, pictureId, voteValue);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
