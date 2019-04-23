package com.xjsaber.netty.im.login.client;

import com.xjsaber.netty.im.login.protocol.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @author xjsaber
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Package packet =
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(Integer.parseInt(UUID.randomUUID().toString()));
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        ByteBuf buffer = PacketCodec
    }
}
