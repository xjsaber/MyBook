package bootstrap08;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author xjsaber
 */
public class MyServerBootstrap {

    public static void main(String[] args){
        // 设置 EventLoopGroup，其将提供用以处理 Channel 事件的 EventLoop
        ServerBootstrap bootstrap = new ServerBootstrap();
        // 创建 ServerBootstrap 以创建ServerSocketChannel，并绑定它
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                // 指定要使用的Channel 实现
                // 设置用于处理已被接受的子Channel 的I/O 和数据的ChannelInboundHandler
                .channel(NioServerSocketChannel.class)
                // 为入站I/O设置ChannelInboundHandler
                .childHandler(new SimpleChannelInboundHandler<ByteBuf>() {

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
                        System.out.println("Received data");
                    }
                });
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8080));
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()){
                System.out.println("Server bound");
            }
            else {
                System.err.println("Bind attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });
    }
}
