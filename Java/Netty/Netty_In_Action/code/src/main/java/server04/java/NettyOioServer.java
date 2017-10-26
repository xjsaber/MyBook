package server04.java;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 *
 * @author xjsaber
 * @date 2017/7/18
 * 使用Netty的阻塞网络处理
 */
public class NettyOioServer {

    public void server(int port) throws Exception {

        final ByteBuf buffer = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi\r\n", Charset.forName("UTF-8")));
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    // OioEventLoopGroup以允许阻塞模式（旧的I/O）
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    // 指定ChannelInitializer，对于每个已接受的的连接都调用他
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                    // 添加ChannelInboundHandlerAdapter以接收和处理事件
                                    new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception{
                                            // 将消息写到客户端，并添加ChannelFutureListener，以便消息一被写完就关闭连接
                                            ctx.writeAndFlush(buffer.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                        }
                                    }
                            );
                        }
                    });
            // 绑定服务器以接受连接
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        }
        finally {
            group.shutdownGracefully().sync();
        }
    }
}
