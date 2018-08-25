package com.xjsaber.javaweb.professionalweb.code;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xjsaber
 */
public class HelloServlet extends HttpServlet {

    private static final String DEFAULT_USER = "Guest";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        if (user == null){
            user = HelloServlet.DEFAULT_USER;
        }

        response.setContentType("text/html");

        response.getWriter().println("Hello World!");
    }

    @Override
    public void init() throws ServletException{
        System.out.println("Servlet " + this.getServletName() + " has started.");
    }

    @Override
    public void destroy(){
        System.out.println("Servlet " + this.getServletName() + " has stopped.");
    }
}
