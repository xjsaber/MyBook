package com.xjsaber.java.network.ch6;

import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;

/**
 * Created by xjsaber on 2017/3/23.
 * 阻塞来自.gov域的cookie，但是接受所有其他域的cookie
 */
public class NoGovernmentCookies implements CookiePolicy{
    @Override
    public boolean shouldAccept(URI uri, HttpCookie cookie) {
        if (uri.getAuthority().toLowerCase().endsWith(".gov") || cookie.getDomain().toLowerCase().endsWith(".gov")){
            return false;
        }
        return true;
    }
}
