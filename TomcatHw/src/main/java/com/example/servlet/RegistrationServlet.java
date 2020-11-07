package com.example.servlet;

import com.example.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {

    private final static String index = "/WEB-INF/view/registration.jsp";
    private static Map<String,User> users;
    private boolean didTryToUseAdminName = false;
    private boolean didTryToUseExistName = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("didTryToUseAdminName", didTryToUseAdminName);
        req.setAttribute("didTryToUseExistName", didTryToUseExistName);
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        HttpSession session = req.getSession();
        users = (Map<String, User>) session.getAttribute("users");
        if (users == null) {
            users = new HashMap<>();
            session.setAttribute("users", users);
        }
        if (!requestIsValid(req)) {
            doGet(req, resp);
        } else {
            final String name = req.getParameter("username");
            final String password = req.getParameter("password");
            final User user = new User(name, password);
            users.put(user.getName(), user);
            if (!didTryToUseExistName && !didTryToUseAdminName) {
                resp.sendRedirect("/successfullyRegistered");
            } else {
                doGet(req, resp);
            }
        }
    }

    private boolean requestIsValid(final HttpServletRequest req) {
        final String name = req.getParameter("username");

        if (name.equals("admin")) {
            didTryToUseAdminName = true;
            didTryToUseExistName = false;
            return false;
        }
        didTryToUseAdminName = false;
        if (users.containsKey(name)) {
            didTryToUseExistName = true;
            return false;
        }
        didTryToUseExistName = false;
        return true;
    }
}
