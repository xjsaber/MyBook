package com.xjsaber.netty.im.login;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author xjsaber
 */
@SuppressWarnings("Duplicates")
public class LoginServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println(new Date() + ": 服务端正在验证登陆信息");
        byte[] bytes = null;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequestPacket){ //
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet;
            if (loginRequestPacket.getUserId() != null && loginRequestPacket.getUserId().length() > 0){
                if ("xjsaber".equals(loginRequestPacket.getUsername()) && "123456".equals(loginRequestPacket.getPassword())){
                    bytes = "登陆成功".getBytes(Charset.forName("utf-8"));
                }
            }
        }
        // 回复数据到客户端
        System.out.println(new Date() + ": 登录结果");
        if (bytes == null) {
            bytes = "登录失败".getBytes(Charset.forName("utf-8"));
        }
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        ctx.channel().writeAndFlush(buffer);
    }
}
