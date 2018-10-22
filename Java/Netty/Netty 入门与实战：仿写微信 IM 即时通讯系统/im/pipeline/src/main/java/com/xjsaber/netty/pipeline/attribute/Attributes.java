package com.xjsaber.netty.pipeline.attribute;

import io.netty.util.AttributeKey;

/**
 * @author xjsaber
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
