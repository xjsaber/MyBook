package xjsaber.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by xjSaber on 2017/1/21.
 */
public class FilterA implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Entering FilterA.doFilter()");
        chain.doFilter(request, response);
        System.out.println("Leaving FilterA.doFilter()");
    }

    @Override
    public void destroy() {

    }
}
