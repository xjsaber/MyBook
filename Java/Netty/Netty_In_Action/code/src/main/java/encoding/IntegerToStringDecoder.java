package encoding;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 扩展了MessageToMessageDecoder<Integer>
 */
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Integer integer, List<Object> list) throws Exception {
        // 将Integer消息转化为它的String表示，并将添加到输出的List中。
        list.add(String.valueOf(integer));
    }
}
