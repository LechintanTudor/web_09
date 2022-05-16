package ro.ubb.imgur.data;

import org.json.JSONArray;
import org.json.JSONObject;
import ro.ubb.imgur.model.Account;
import ro.ubb.imgur.model.PictureWithStats;
import ro.ubb.imgur.persistence.Database;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "picturesWithStats", value = "/api/pictures/stats")
public class PicturesWithStats extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        Account account = (Account) request.getSession().getAttribute("account");

        try {
            if (account == null) {
                PrintWriter out = new PrintWriter(response.getOutputStream(), true);
                out.println("null");
                return;
            }

            long limit = Long.parseLong(request.getParameter("limit"));

            List<PictureWithStats> picturesWithStats = Database.getHighestVotedPictures(limit);
            JSONArray picturesWithStatsJSON = new JSONArray();

            for (PictureWithStats picture : picturesWithStats) {
                JSONObject pictureJSON = new JSONObject();
                pictureJSON.put("filePath", picture.filePath);
                pictureJSON.put("authorName", picture.authorName);
                pictureJSON.put("totalVotes", picture.totalVotes);
                picturesWithStatsJSON.put(pictureJSON);
            }

            PrintWriter out = new PrintWriter(response.getOutputStream(), true);
            out.println(picturesWithStatsJSON);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
