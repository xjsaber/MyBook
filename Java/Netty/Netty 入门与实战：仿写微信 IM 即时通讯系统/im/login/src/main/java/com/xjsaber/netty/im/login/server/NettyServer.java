package com.xjsaber.netty.im.login.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xjsaber
 */
public class NettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup boss = new DefaultEventLoopGroup();
        EventLoopGroup worker = new DefaultEventLoopGroup();

        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });

    }
}
