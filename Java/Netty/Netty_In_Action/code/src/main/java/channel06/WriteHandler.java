package channel06;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author xjsaber
 */
public class WriteHandler extends ChannelHandlerAdapter {

    private ChannelHandlerContext ctx;

    /**
     * 存储到ChannelHandlerContext的引用以供稍后使用
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        this.ctx = ctx;
    }

    /**
     * 使用之前存储的到 ChannelHandlerContext的引用来发送消息
     * @param msg
     */
    public void send(String msg){
        ctx.writeAndFlush(msg);
    }
}
