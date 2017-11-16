package initizlizer11;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * 解码器基于长度的协议
 * @author xjsaber
 */
public class LengthFieldBasedFrameDecoder extends ChannelInitializer<Channel>{

    public LengthFieldBasedFrameDecoder(int i, int i1, int i2) {
        //TODO 构造函数确实，代码
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // 使用LengthFieldBasedFrameDecoder解码将帧长度编码到帧起始的前8个字节中的消息
        pipeline.addLast(new LengthFieldBasedFrameDecoder(64 * 1024, 0, 8));
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            // 处理帧的数据
            // Do something with the frame
        }
    }
}
