package ro.ubb.imgur.data;

import ro.ubb.imgur.model.Account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

@WebServlet(name = "pictures", value = "/api/pictures")
public class Pictures extends HttpServlet {
    private static final String PICTURE_DIRECTORY = "C:/Users/lechi/OneDrive/Pictures/Tomcat/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        try {
            if (account == null) {
                return;
            }

            String pictureUuid = request.getParameter("uuid");
            String pictureFilePath = PICTURE_DIRECTORY + pictureUuid + ".jpg";
            byte[] pictureBytes = {};

            try (FileInputStream fis = new FileInputStream(new File(pictureFilePath))) {
                pictureBytes = fis.readAllBytes();
            }

            response.setContentType("image/jpeg");
            response.getOutputStream().write(pictureBytes);
            response.getOutputStream().flush();
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
