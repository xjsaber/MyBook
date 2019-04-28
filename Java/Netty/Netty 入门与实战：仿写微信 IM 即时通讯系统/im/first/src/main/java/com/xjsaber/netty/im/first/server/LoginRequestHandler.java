package com.xjsaber.netty.im.first.server;

import com.xjsaber.netty.im.first.protocol.LoginRequestPacket;
import com.xjsaber.netty.im.first.protocol.LoginResponsePacket;
import com.xjsaber.netty.im.first.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author xjsaber
 */
@SuppressWarnings("Duplicates")
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");
        // 登录流程
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());

        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": 登录成功!");
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }
        // 登录响应
        ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
        ctx.channel().writeAndFlush(responseByteBuf);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
