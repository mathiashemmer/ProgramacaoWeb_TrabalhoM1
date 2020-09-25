package com.mathiashemmer.trabalhom1;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mathias
 */

@WebFilter(filterName = "Auth", urlPatterns = {"/Auth/*"})
public class Auth implements Filter {
      
    public Auth() {
    }

    public boolean NullOrEmpty(String prop){
        if(prop == null) return false;
        else if(prop instanceof String && prop.isBlank()) return false;
        return true;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest http_req = (HttpServletRequest)request;
        HttpServletResponse http_res = (HttpServletResponse)response;
        
        boolean session_is_valid = ValidateSession(http_req);
        boolean cookies_are_valid = false;
        if(!session_is_valid){
            cookies_are_valid = ValidateCookies(http_req, http_res);
        }
        
        if(cookies_are_valid || session_is_valid){
            chain.doFilter(request, response);
        }else{
            String base_url = http_res.encodeRedirectURL(http_req.getContextPath() + "/");
            http_res.sendRedirect(base_url);
        }
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
       
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
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("username") ||cookie.getName().equals("password")){
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        http_res.addCookie(cookie);
                    }
                }
            }
        }
        
        return false;
    }   
   
    private boolean ValidateSession(HttpServletRequest http_req) {
        String current_username = null;
        String current_password = null;
        HttpSession session = http_req.getSession();
        
        current_username = (String) session.getAttribute("username");
        current_password = (String) session.getAttribute("password");
        
        return (Constants.usuarios().get(current_username) != null && Constants.usuarios().get(current_username).equals(current_password));
    }
}
