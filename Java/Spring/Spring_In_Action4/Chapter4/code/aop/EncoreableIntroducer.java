package com.xjsaber.spring.aop;

import org.aspectj.lang.annotation.DeclareParents;

/**
 * Created by xjsaber on 2017/4/23.
 *
 */
public class EncoreableIntroducer {

    @DeclareParents(value = "com.xjsaber.spring.aop.Performance+", defaultImpl = DefaultEncoreable.class)
    public static Encoreable encoreable;
}
