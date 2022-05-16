package ro.ubb.imgur.page;

import ro.ubb.imgur.model.Account;
import ro.ubb.imgur.persistence.Database;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "pictureUploaded", value = "/pictures/upload-result")
public class PictureUploadedPage extends HttpServlet {
    private static final String PICTURE_DIRECTORY = "C:/Users/lechi/OneDrive/Pictures/Tomcat/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        try {
            if (account == null) {
                getServletContext()
                        .getRequestDispatcher("/account/login-error.jsp")
                        .forward(request, response);
                return;
            }

            for (Part part : request.getParts()) {
                String fileName = UUID.randomUUID().toString();
                String filePath = PICTURE_DIRECTORY + fileName + ".jpg";
                part.write(filePath);

                Database.createPicture(fileName, account.id);
            }

            getServletContext()
                    .getRequestDispatcher("/account/home.jsp")
                    .forward(request, response);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
