package com.xjsaber.netty.message.attribute;

import io.netty.util.AttributeKey;

/**
 * @author xjsaber
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
