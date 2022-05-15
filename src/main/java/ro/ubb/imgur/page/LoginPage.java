package ro.ubb.imgur.page;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", value = "/login")
public class LoginPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            getServletContext()
                    .getRequestDispatcher("/account/login.jsp")
                    .forward(request, response);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
