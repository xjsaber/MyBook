package http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LineBasedFrameDecoder;

public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 该 LineBasedFrameDecoder将提取的帧转发给下一个ChannelInboundHandler
        pipeline.addLast(new LineBasedFrameDecoder(64 * 1024));

        // 添加FrameHandler以接收帧
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {
        // 传入单个帧的内容
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            // Do something with the data extracted from frame
        }
    }
}
