package channel06;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author xjsaber
 */
@ChannelHandler.Sharable
public class DiscardInboundHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        // 通过调用ReferenceCountUtil.release() 方法释放资源
        ReferenceCountUtil.release(msg);
    }
}
