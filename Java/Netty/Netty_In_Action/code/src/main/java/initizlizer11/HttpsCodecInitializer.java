package initizlizer11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;


/**
 * @author xjsaber
 */
public class HttpsCodecInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean isClient;

    public HttpsCodecInitializer(SslContext context, boolean isClient) {
        this.context = context;
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        SSLEngine engine = context.newEngine(channel.alloc());
        // 将SslHandler添加到ChannelPipeline中以使用HTTPS
        pipeline.addFirst("ssl", new SslHandler(engine));

        if (isClient) {
            // 客户端
            pipeline.addLast("codec", new HttpClientCodec());
        }
        else {
            // 服务器
            pipeline.addLast("codec", new HttpServerCodec());
        }
    }
}
