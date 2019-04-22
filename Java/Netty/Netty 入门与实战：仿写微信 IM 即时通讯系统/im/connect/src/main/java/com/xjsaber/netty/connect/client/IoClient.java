package com.xjsaber.netty.connect.client;

import com.xjsaber.netty.connect.handler.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author xjsaber
 */
public class IoClient {

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        System.out.println("Client:连接成功");
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });
        bootstrap.connect("localhost", 8000).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("1:连接成功！");
            } else {
                System.out.println("2:连接失败！");
            }
        });
    }
}
