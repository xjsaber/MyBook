package bootstrap;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioDatagramChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;

public class BootstrapDemo {

    // 创建一个AttributeKey以标识该属性
    private final static AttributeKey<Integer> id = AttributeKey.newInstance("ID");;
    public static void main(String[] args){


    }

    /**
     * tcp service
     */
    public void tcpStart(){
        Bootstrap bootstrap = new Bootstrap();
        // 设置EventLoopGroup，其提供了用以处理Channel事件的EventLoop
        // 设置用以处理Channel的I/O以及数据的ChannelInBoundHandler
        bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class).handler(new SimpleChannelInboundHandler<ByteBuf>() {

            @Override
            public void channelRegistered(ChannelHandlerContext ctx) throws Exception{
                // 使用AttributeKey检索属性以及它的值
                Integer idValue = ctx.channel().attr(id).get();
                // do something with the idValue
            }

            @Override
            protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
                System.out.println("Received data");
            }
        });
        // 设置ChannelOption，其将在connect()或者bind()方法被调用时被设置到已经创建的Channel上
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
        bootstrap.attr(id, 123456);
        // 使用配置好的Bootstrap实例连接欸到远程主机
        ChannelFuture future = bootstrap.connect(new InetSocketAddress("www.baidu.com", 80));
        future.syncUninterruptibly();
    }

    /**
     * udp service
     */
    public void udpStart(){
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new OioEventLoopGroup()).channel(OioDatagramChannel.class).handler(new SimpleChannelInboundHandler<DatagramPacket>() {
            @Override
            protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket) throws Exception {
                // Do something with the packet
            }
        });
        ChannelFuture future =  bootstrap.bind(new InetSocketAddress(0));
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("Channel bound");
            }
            else {
                System.err.println("Bind attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });
    }

    /**
     * 关闭netty
     */
    public void close(){
        // 创建处理I/O的EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        // 创建一个Bootstrap
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class);
        // shutdownGracefully()方法将释放所有的资源，并且关闭所有的当前正在使用中的Channel
        Future<?> future = group.shutdownGracefully();
        // block util the group has shutdown
        future.syncUninterruptibly();
    }
}
