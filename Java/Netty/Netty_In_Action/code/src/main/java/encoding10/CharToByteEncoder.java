package encoding10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xjsaber
 */
public class CharToByteEncoder extends MessageToByteEncoder<Character>{

    @Override
    protected void encode(ChannelHandlerContext ctx, Character msg, ByteBuf out) throws Exception {
        // 将Character解码为char，并将其写入到出站ByteBuf中
        out.writeChar(msg);
    }
}
