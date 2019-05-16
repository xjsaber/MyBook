package com.xjsaber.learn.spring.springboot.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author xjsaber
 */
@Component
@Scope((ConfigurableBeanFactory.SCOPE_PROTOTYPE))
public class ScopeBean {

}
