package com.xjsaber.java.netty.guide.ch4;

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
     * TODO 解决
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        // 通过ByteBuf的readableBytes方法可以获取缓冲区可读的字节数，根据可读的字节数，根据可读的字节数创建byte数组，通过ByteBuf的readBytes方法将缓冲区中的字节数组复制到新建的byte数组中，最后通过new String构造函数获取请求消息。这时对请求消息进行判断，如果是“QUERY TIME ORDER”则创建应答消息，通过ChannelHandlerContxt的write方法异步发送应答消息给客户端。
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8).substring(0, req.length - System.getProperty("line.separator").length());
        System.out.println(" The time server receive order: " + body + " ; the counter is : " + ++counter);
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
