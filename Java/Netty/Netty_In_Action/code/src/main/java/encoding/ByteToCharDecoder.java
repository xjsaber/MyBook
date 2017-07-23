package encoding;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 扩展了ByteToMessageDecoder
 */
public class ByteToCharDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes() >=  2) {
            // 将一个或者多个Character对象添加到传出的List中
            out.add(in.readChar());
        }
    }
}
