package com.xjsaber.java.netty.guide.ch8;

/**
 * @author xjsaber
 */
public class SubReqServer {

    public void bind(int port) throws Exception {
        // 配置服务端的NIO线程组
//        EventLoopGroup boosGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(boosGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .option(ChannelOption.SO_BACKLOG, 100)
//                    .handler(new LoggingHandler(LogLevel.INFO))
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        public void initChannel(SocketChannel ch){
//                            ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
//                            ch.pipeline().addLast(new ProtobufDecoder(SubscribeRe));
//                            ch.pipeline().addLast(new ProtobufEncoder());
//                            ch.pipeline().addLast(new SubReqServerHandler());
//                        }
//                    })
//        } catch (Exception ex){
//
//        }
    }
}
