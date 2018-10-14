package com.xjsaber.diveinsprintboot.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/my/servlet",
        asyncSupported=true)
//TODO asyncSupported无效
public class MyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        AsyncContext context = req.getAsyncContext();
        context.start(() -> {
            try {
                resp.getWriter().println("Hello World");
                // 触发完成
                context.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}

