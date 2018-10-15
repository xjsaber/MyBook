package com.xjsaber.java.web.service.site;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener, HttpSessionIdListener, ServletContextListener {
    private static final Logger log = LogManager.getLogger();

    @Inject SessionRegistry sessionRegistry;

    /**
     * 可以使用监听器的contextInitialized方法从ServletContext中获得根应用上下文，
     * 从应用上下文获得bean工厂，并将SessionListener实例配置为根应用上下文中的bean。
     *
     * 当contextInitialized方法执行完成之后，SessionRegistry实现将被注入，SessionListener可以立即开始使用它。
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext(
                        servletContextEvent.getServletContext());
        AutowireCapableBeanFactory factory =
                context.getAutowireCapableBeanFactory();
        factory.autowireBeanProperties(this,
                AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
        factory.initializeBean(this, "sessionListener");
        log.info("Session listener initialized in Spring application context.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("Session listener destroyed in Spring application context.");
    }

    @Override
    public void sessionIdChanged(HttpSessionEvent httpSessionEvent, String s) {
        log.debug("Session " + httpSessionEvent.getSession().getId() + " changed.");
        this.sessionRegistry.updateSessionId(httpSessionEvent.getSession(), s);
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.debug("Session " + httpSessionEvent.getSession().getId() + " created.");
        this.sessionRegistry.addSession(httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.debug("Session " + httpSessionEvent.getSession().getId() + " destroyed.");
        this.sessionRegistry.removeSession(httpSessionEvent.getSession());
    }
}
