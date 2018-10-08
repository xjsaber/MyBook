package com.xjsaber.netty.im.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

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
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1", 8080).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("连接成功！");
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
