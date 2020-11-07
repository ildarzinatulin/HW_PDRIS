package com.example.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.User;

public class MainServlet extends HttpServlet {
    private final static String index = "/WEB-INF/view/index.jsp";
    private static Map<String,User> users;
    private boolean isUserExist = true;
    private boolean isPasswordCorrect = true;
    private String lastName = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isUserExist", isUserExist);
        req.setAttribute("isPasswordCorrect", isPasswordCorrect);
        req.setAttribute("lastName", lastName);
        isUserExist = true;
        isPasswordCorrect = true;
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        users = (Map<String, User>) session.getAttribute("users");
        if (users == null) {
            users = new HashMap<>();
            session.setAttribute("users", users);
        }
        System.out.println(users.keySet());
        final String name = req.getParameter("username");
        final String password = req.getParameter("password");
        lastName = name;
        if (!users.containsKey(name)) {
            isUserExist = false;
        } else {
            isUserExist = true;
           if (!users.get(name).getPassword().equals(password)) {
               isPasswordCorrect = false;
           } else {
               isPasswordCorrect = true;
           }
        }
        if (isUserExist && isPasswordCorrect) {
            resp.sendRedirect("/successfullyLogin");
        } else {
            doGet(req, resp);
        }
    }
}
