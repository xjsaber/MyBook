package encoding10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * 扩展ByteToMessageDecoder 以将字节解码为消息
 */
public class SafeByteToMessageDecoder extends ByteToMessageDecoder{

    private static final int MAX_FRAME_SIZE = 1024;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readable = byteBuf.readableBytes();
        if (readable > MAX_FRAME_SIZE) {
            byteBuf.skipBytes(readable);
            // 跳出所有的可读字节，抛出TooLongFrameException并通知ChannelHandler
            throw new TooLongFrameException("Frame too big");
        }
    }
}
