package com.xjsaber.java.concurrency.ch2;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;

/**
 * @author xjsaber
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {

    public void init(ServletConfig config) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest req, ServletResponse resp){
//        BigInteger i = extractFromRequest(req);
//        BigInteger[] factors = factor(i);
//        encodeIntoResponse(resp, factors);
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
