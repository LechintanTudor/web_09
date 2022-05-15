package ro.ubb.imgur.controller;

import ro.ubb.imgur.model.Account;
import ro.ubb.imgur.persistence.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "myAccount", value = "/accounts/me")
public class MyAccountController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = Database.authenticate(username, password);

        try {
            if (account != null) {
                HttpSession session = request.getSession();
                session.setAttribute("account", account);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                requestDispatcher.forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("login-error.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
    }
}
