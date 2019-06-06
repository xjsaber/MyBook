package com.xjsaber.learn.spring.springboot.service.impl;

import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;

/**
 * @author xjsaber
 */
@ServerEndpoint("/ws")
@Service
public class WebSocketServiceImpl {

    private static int onlineCount = 0;
    private
}
