package http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;


public class SslChannelInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean startTls;

    public SslChannelInitializer(SslContext context, boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }


    @Override
    protected void initChannel(Channel channel) throws Exception {
        SSLEngine engine = context.newEngine(channel.alloc());
        channel.pipeline().addFirst("Ssl", new SslHandler(engine, startTls));
    }
}
