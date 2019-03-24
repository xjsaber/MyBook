package com.xjsaber.netty.im.handler;

import com.xjsaber.netty.im.login.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String msg = new Date() + ": 客户端开始登录";
        System.out.println(msg);

        // 写数据
        ctx.channel().writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(new Date() + ": 收到服务端的消息: " + msg);
    }
}
