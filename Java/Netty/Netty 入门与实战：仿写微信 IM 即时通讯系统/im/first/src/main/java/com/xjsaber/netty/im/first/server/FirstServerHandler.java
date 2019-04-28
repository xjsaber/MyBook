package com.xjsaber.netty.im.first.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;


/**
 * @author xjsaber
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf buffer = (ByteBuf) msg;

        System.out.println(new Date() + "：服务器读到数据 ->" + buffer.toString(Charset.forName("utf-8")));
    }
}
