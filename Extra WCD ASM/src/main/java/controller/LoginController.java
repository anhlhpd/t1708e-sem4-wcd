package controller;

import entity.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Account account = ofy().load().type(Account.class).id(email).now();
        if(account.getPassword() == password){
            HttpSession session = req.getSession();
            session.setAttribute("currentAccount", account);
        }
        resp.sendRedirect("/account");
    }
}
