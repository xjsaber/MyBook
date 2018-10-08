package com.xjsaber.java.netty.guide.ch4.solution;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * 事件处理 Netty时间服务器服务端 TimeServerHandler
 * @author xjsaber
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;


    /**
     * 出现粘包问题，利用LineBasedFrameDecoder解决TCP粘包问题
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        String body = (String) msg;
        System.out.println("The time server receive order : " + body + " ; the counter is : " +  ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        ctx.close();
    }
}
