package com.xjsaber.netty.im.first.util;

import com.xjsaber.netty.im.first.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author xjsaber
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
