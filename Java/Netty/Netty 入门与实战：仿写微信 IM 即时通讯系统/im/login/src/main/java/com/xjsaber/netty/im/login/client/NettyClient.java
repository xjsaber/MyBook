package com.xjsaber.netty.im.login.client;

import com.xjsaber.netty.im.login.server.ServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author xjsaber
 */
public class NettyClient {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup worker = new DefaultEventLoopGroup();

        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
    }
}
