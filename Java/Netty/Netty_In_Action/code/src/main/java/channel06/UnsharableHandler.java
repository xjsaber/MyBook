package channel06;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 拥有状态，即用于跟踪方法调用次数的实例变量count。将这个类的一个实例添加到ChannelPipeline将极有可能在它被多个并发的Channel访问时导致问题。（当然，这个简单的问题可以通过使channelRead()方法变为同步方法来修正。）
 * @author xjsaber
 */
public class UnsharableHandler extends ChannelInboundHandlerAdapter {

    private int count;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        count++;
        System.out.println("channelRead(...) called the "
                + count + " time");
        // 记录方法调用，并转发给下一个ChannelHandler
        ctx.fireChannelRead(msg);
    }
}
