package com.example.servlet;

import com.example.model.User;
import com.example.service.UserStorageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private final static String INDEX = "/WEB-INF/view/registration.jsp";

    private UserStorageService userStorageService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(INDEX).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        userStorageService = (UserStorageService) session.getAttribute("userStorageService");
        if (userStorageService == null) {
            userStorageService = new UserStorageService();
            session.setAttribute("userStorageService", userStorageService);
        }

        User user = new User(req.getParameter("username"), req.getParameter("password"));
        if (!validateAuthorisationData(user, req)) {
            doGet(req, resp);
        } else {
            userStorageService.add(user);
            resp.sendRedirect("/successfullyRegistered");
        }
    }

    private boolean validateAuthorisationData(final User user, HttpServletRequest req) {
        if (user.getName().equals("admin")) {
            req.setAttribute("didTryToUseAdminName", true);
            req.setAttribute("didTryToUseExistName", false);
            return false;
        }
        if (userStorageService.isUserExist(user)) {
            req.setAttribute("didTryToUseAdminName", false);
            req.setAttribute("didTryToUseExistName", true);
            return false;
        }
        req.setAttribute("didTryToUseAdminName", false);
        req.setAttribute("didTryToUseExistName", false);
        return true;
    }
}
