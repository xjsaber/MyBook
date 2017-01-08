package com.xjsaber;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xjSaber on 2017/1/7.
 */
@WebServlet(
        name = "contextParameterServlet",
        urlPatterns = {"/contextParameters"}
)
public class ContextParameterServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext c = this.getServletContext();
        PrintWriter writer = response.getWriter();

        writer.append("settingOne:").append(c.getInitParameter("settingOne"))
                .append(",settingTwo:").append(c.getInitParameter("settingTwo"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request, response);
    }
}
