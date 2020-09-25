/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mathiashemmer.trabalhom1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mathias
 */
@WebServlet(name = "RestrictAcessPage", urlPatterns = {"/Auth/RestrictAcessPage"})
public class RestrictAcessPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/RestrictAcessPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.addCookie(eraseCookie("username", request.getContextPath()));
        response.addCookie(eraseCookie("password", request.getContextPath()));
        session.setAttribute("username", "");
        session.setAttribute("password", "");
        String base_url = response.encodeRedirectURL(request.getContextPath());
        response.sendRedirect(base_url);
    }
    
    public Cookie eraseCookie(String CookieName, String path) {
        Cookie cookie = new Cookie(CookieName, "");
        cookie.setPath(path);
        cookie.setMaxAge(0);
        return cookie;
    }
}
