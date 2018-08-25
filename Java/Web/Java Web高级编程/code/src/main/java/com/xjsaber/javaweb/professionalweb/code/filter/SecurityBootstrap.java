package com.xjsaber.javaweb.professionalweb.code.filter;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import javax.servlet.SessionTrackingMode;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author xjsaber
 */
public class SecurityBootstrap extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected Set<SessionTrackingMode> getSessionTrackingModes(){
        return EnumSet.of(SessionTrackingMode.SSL);
    }
}
