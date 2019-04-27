package com.xjsaber.netty.messagev2.client;

import com.xjsaber.netty.messagev2.protocol.PacketCodeC;
import com.xjsaber.netty.messagev2.protocol.request.MessageRequestPacket;
import com.xjsaber.netty.messagev2.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author xjsaber
 */
@SuppressWarnings("Duplicates")
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(new Date() + ": 收到服务端的消息: " + msg.getMessage());
    }
}
