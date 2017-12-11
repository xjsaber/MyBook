package com.xjsaber.java.netty.guide.ch7;

import io.netty.channel.ChannelHandlerAdapter;

public class EchoClientHandler extends ChannelHandlerAdapter {

    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

//    @Override
//    public void channelActive(ChannelHandlerContext ctx){
//
//    }

//    private UserInfo[] userInfo(){
//        UserInfo[] userInfos = new UserInfo[sendNumber];
//        UserInfo userInfo = null;
//        for (int i = 0; i < sendNumber; i++){
//            userInfo = new UserInfo();
//            userInfo.setA
//        }
//    }
}
