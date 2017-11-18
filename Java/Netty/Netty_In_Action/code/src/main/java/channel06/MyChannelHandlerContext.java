package channel06;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

/**
 * @author xjsaber
 */
public class MyChannelHandlerContext {

    public static void main(String[] args){
        ChannelHandlerContext ctx = null;
        assert ctx != null;
        Channel channel = ctx.channel();
        channel.write(Unpooled.copiedBuffer("Netty in Action",
                CharsetUtil.UTF_8));
    }
}
