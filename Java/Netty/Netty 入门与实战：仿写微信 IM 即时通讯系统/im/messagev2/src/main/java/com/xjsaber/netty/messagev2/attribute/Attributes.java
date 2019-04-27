package com.xjsaber.netty.messagev2.attribute;

import io.netty.util.AttributeKey;

/**
 * @author xjsaber
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
