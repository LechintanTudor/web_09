package ro.ubb.imgur.page;

import ro.ubb.imgur.model.Account;
import ro.ubb.imgur.persistence.Database;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "home", value = "/home")
public class HomePage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = Database.authenticate(username, password);

        try {
            if (account == null) {
                getServletContext()
                        .getRequestDispatcher("/account/login-error.jsp")
                        .forward(request, response);

                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("account", account);

            getServletContext()
                    .getRequestDispatcher("/account/home.jsp")
                    .forward(request, response);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
