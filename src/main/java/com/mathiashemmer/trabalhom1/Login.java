/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mathiashemmer.trabalhom1;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Login", urlPatterns = {"/"})
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String req_username = (String) request.getParameter("txtUserName");
        String req_password = (String) request.getParameter("txtPassword");
        String base_url = response.encodeRedirectURL(request.getContextPath() + "/");
        Boolean req_set_auth = (Boolean) ("1".equals((String)request.getParameter("keepMeIn")));
        HttpSession session = request.getSession();

        if(Constants.usuarios().get(req_username) != null && Constants.usuarios().get(req_username).equals(req_password)){
                session.setAttribute("username", req_username);
                session.setAttribute("password", req_password);
            if(req_set_auth){
                Cookie c_password = new Cookie("username", req_username);
                c_password.setMaxAge(60*60*24);
                c_password.setPath(request.getContextPath());
                Cookie c_username = new Cookie("password", req_password);
                c_username.setMaxAge(60*60*24);
                c_username.setPath(request.getContextPath());
                response.addCookie(c_username);
                response.addCookie(c_password);
            }
            
            response.sendRedirect(base_url + "Auth/RestrictAcessPage");
        }else{
            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String base_url = response.encodeRedirectURL(request.getContextPath() + "/");
        if(ValidateCookies(request, response)){
            response.sendRedirect(base_url + "Auth/RestrictAcessPage");
        }else{
            request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
        }
    }
    
    private boolean ValidateCookies(HttpServletRequest http_req, HttpServletResponse http_res){
        String current_username = null;
        String current_password = null;
        
        HttpSession session = http_req.getSession();
        
        Cookie[] cookies = http_req.getCookies();
        if (cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("username"))
                    current_username = cookie.getValue();
                if(cookie.getName().equals("password"))
                    current_password = cookie.getValue();
            }
            if(Constants.usuarios().get(current_username) != null && Constants.usuarios().get(current_username).equals(current_password)){
                session.setAttribute("username", current_username);
                session.setAttribute("password", current_password);
                return true;
            }else{
                http_res.addCookie(eraseCookie("username"));
                http_res.addCookie(eraseCookie("password"));
            }
        }
        return false;
    }
    
    private Cookie eraseCookie(String CookieName) {
        Cookie cookie = new Cookie(CookieName, "");
        cookie.setMaxAge(0);
        return cookie;
    }
}
