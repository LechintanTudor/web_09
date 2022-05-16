package ro.ubb.imgur.page;

import ro.ubb.imgur.model.Account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "picturesPage", value = "/pictures/gallery")
public class PictureGalleryPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Account account = (Account) request.getSession().getAttribute("account");

        try {
            if (account == null) {
                getServletContext()
                        .getRequestDispatcher("/account/login-error.jsp")
                        .forward(request, response);

                return;
            }

            getServletContext()
                    .getRequestDispatcher("/pictures/gallery.jsp")
                    .forward(request, response);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
