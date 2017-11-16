package encoding10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 扩展了ByteToMessageDecoder
 * @author xjsaber
 */
public class ByteToCharDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes() >=  2) {
            // 将一个或者多个Character对象添加到传出的List中
            // decode()方法一次将从ByteBuf中提取2个字节，并将它们作为char写入到List中，其将会自动装箱为Character对象
            out.add(in.readChar());
        }
    }
}
