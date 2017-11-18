package channel06;

import io.netty.channel.*;

/**
 * 添加 ChannelFutureListener 到 ChannelPromise
 * @author xjsaber
 */
public class OutboundExceptionHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg,
                      ChannelPromise promise) {
        promise.addListener((ChannelFutureListener) f -> {
            if (!f.isSuccess()) {
                f.cause().printStackTrace();
                f.channel().close();
            }
        });
    }
}
