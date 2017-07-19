package channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * Created by xjsaber on 2017/7/17.
 *
 */
public class MyChannel {

    public static void main(String[] args){
        EventLoopGroup group = new NioEventLoopGroup();
        Channel channel = new NioSocketChannel();
        ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
        ChannelFuture cf = channel.writeAndFlush(buf);
        cf.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("Write successful");
            }
            else {
                System.err.println("Write error");
                channelFuture.cause().printStackTrace();
            }
        });
    }
}
