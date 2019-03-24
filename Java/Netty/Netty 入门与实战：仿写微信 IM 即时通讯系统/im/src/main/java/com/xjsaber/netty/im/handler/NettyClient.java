package com.xjsaber.netty.im.handler;

import com.xjsaber.netty.im.login.PacketCodeC;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;
import java.util.Scanner;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        // 引导类，负责启动客户端以及连接服务端（服务端相对的是ServerBootStrap）
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                // 1. 指定县城模型
                .group(group)
                // 2. 指定IO类型为NIO
                .channel(NioSocketChannel.class)
                // 3. IO处理逻辑
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new StringEncoder());
                        channel.pipeline().addLast(new ClientHandler());
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1", 8081).addListener(future -> {
            int retry = 5;
            if (future.isSuccess()){
                System.out.println("连接成功！");
            }
            else if (retry == 0) {
                System.err.println("重试次数的已用完，放弃连接！");
            }
            else {

                System.out.println("连接失败！");
            }
        }).channel();

        while (true){
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
