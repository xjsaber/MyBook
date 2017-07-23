package encoding;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 扩展ByteToMessageDecoder类，以将字节解码为特定的格式
 */
public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 检查是否至少由4字节可读（一个int的字节长度）
        if (byteBuf.readableBytes() > 4) {
            // 从入站ByteBuf中读取一个int，并将其添加到解码消息的List中。
            list.add(byteBuf.readInt());
        }
    }
}
