package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.User;
import com.example.service.UserStorageService;

public class MainServlet extends HttpServlet {

    private final static String INDEX = "/WEB-INF/view/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(INDEX).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserStorageService userStorageService = (UserStorageService) session.getAttribute("userStorageService");
        if (userStorageService == null) {
            userStorageService = new UserStorageService();
            session.setAttribute("userStorageService", userStorageService);
        }

        User user = new User(req.getParameter("username"), req.getParameter("password"));
        boolean isUserExist = false;
        boolean isPasswordCorrect = false;
        if (userStorageService.isUserExist(user)) {
            isUserExist = true;
            if (userStorageService.isPasswordCorrect(user)) {
                isPasswordCorrect = true;
            }
        }

        req.setAttribute("isUserExist", isUserExist);
        req.setAttribute("isPasswordCorrect", isPasswordCorrect);
        req.setAttribute("lastUserName", user.getName());

        if (isUserExist && isPasswordCorrect) {
            resp.sendRedirect("/successfullyLogin");
        } else {
            doGet(req, resp);
        }
    }
}
