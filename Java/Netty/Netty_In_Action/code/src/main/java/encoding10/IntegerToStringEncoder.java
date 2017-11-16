package encoding10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * 编码器将每个出站Integer的String表示添加到该List中
 * @author xjsaber
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer>{

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
