package com.xjsaber.java.concurrency.ch2;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author xjsaber
 */
public class UnsafeCountingFactorizer implements Servlet{


    public void init(ServletConfig config) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        BigInteger i = extractFromRequest(req);
//        BigInteger[] factors = factor(i);
//        ++count;
//        encodeIntoResponse(resp, factors);
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
