package com.xjsaber.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by xjsaber on 2017/4/8.
 * Discards any incoming data.
 * 1.启动服务器应先创建一个 ServerBootstrap 对象，因为使用 NIO，
 所以指定 NioEventLoopGroup 来接受和处理新连接，指定通道类型为 NioServerSocketChannel，设置
 InetSocketAddress 让服务器监听某个端口已等待客户端连接
 * 2.调用 childHandler 放来指定连接后调用的 ChannelHandler，这个方法传 ChannelInitializer 类型的参
 数，ChannelInitializer 是个抽象类，所以需要实现 initChannel 方法，这个方法就是用来设置 ChannelHandler。
 * 3.最后绑定服务器等待直到绑定完成，调用 sync()方法会阻塞直到服务器完成绑定，然后服务器等待通道关闭，
 因为使用 sync()，所以关闭操作也会被阻塞。现在你可以关闭 EventLoopGroup 和释放所有资源，包括创建的线程。
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port){
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // create ServerBootstrap instance
            ServerBootstrap b = new ServerBootstrap();
            //Specifies NIO transport, local socket address  
            //Adds handler to channel pipeline  
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //Binds server, waits for server to close, and releases resources
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + "started and listen on “" + f.channel().localAddress());
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        }
        finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        new EchoServer(65535).start();
    }
}
