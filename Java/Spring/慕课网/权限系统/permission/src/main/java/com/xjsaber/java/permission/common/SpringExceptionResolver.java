package com.xjsaber.java.permission.common;

import com.xjsaber.java.permission.exception.PermissionException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        String url = request.getRequestURL().toString();
        ModelAndView mv = null;
        String defaultMsg = "System error";

        // .json .page
        // 所有要求项目中所有使用json数据的，都是用.json结尾
        if (url.endsWith(".json")){
            if (e instanceof PermissionException) {
                JsonData result = JsonData.fail(e.getMessage());
                //对应spring-servlet中的jsonView
                mv = new ModelAndView("jsonView", result.toMap());
            } else {
                log.error("unknown json exception, url:" + url, e);
                JsonData result = JsonData.fail(defaultMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }
        } else if (url.endsWith(".page")){
            log.error("unknown page exception, url:" + url, e);
            // 所有要求项目中所有使用json数据的，都是用.page结尾
            JsonData result = JsonData.fail(e.getMessage());
            mv = new ModelAndView("exception", result.toMap());
        } else {
            log.error("unknown exception, url:" + url, e);
            JsonData result = JsonData.fail(e.getMessage());
            mv = new ModelAndView("jsonView", result.toMap());
        }
        return mv;
    }
}
