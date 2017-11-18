package channel06;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 由于 SimpleChannelInboundHandler 会自动释放资源，所以你不应该存储指向任何消
 息的引用供将来使用，因为这些引用都将会失效
 * @author xjsaber
 */
@ChannelHandler.Sharable
public class SimpleDiscardHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //No need to do anything special 不需要任何显式的资源释放
    }
}
