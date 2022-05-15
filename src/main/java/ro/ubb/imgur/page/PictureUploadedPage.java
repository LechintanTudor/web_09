package ro.ubb.imgur.page;

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
    private static final String PHOTO_DIRECTORY = "C:\\Users\\lechi\\OneDrive\\Pictures\\Tomcat\\";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            for (Part part : request.getParts()) {
                String fileName = UUID.randomUUID().toString() + ".jpg";
                String filePath = PHOTO_DIRECTORY + fileName;
                part.write(filePath);
            }

            getServletContext()
                    .getRequestDispatcher("/account/home.jsp")
                    .forward(request, response);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
