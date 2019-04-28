package com.xjsaber.netty.im.first.server;

import com.xjsaber.netty.im.first.client.LoginResponseHandler;
import com.xjsaber.netty.im.first.client.MessageResponseHandler;
import com.xjsaber.netty.im.first.codec.PacketDecoder;
import com.xjsaber.netty.im.first.codec.PacketEncoder;
import com.xjsaber.netty.im.first.codec.Spliter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Date;

/**
 * @author xjsaber
 */
public class NettyServer {

    public static void main(String[] args){
        ServerBootstrap bootstrap = new ServerBootstrap();

        // 监听接口
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 处理每条连接的数据读写的线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 配置两大线程组
        bootstrap.group(bossGroup, workerGroup)
                // 指定IO模型
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
//                        ch.pipeline().addLast(new FirstServerHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bind(bootstrap, 8000);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
