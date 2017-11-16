package initizlizer11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author xjsaber
 */
public class HttpPipelineInitializer extends ChannelInitializer<Channel> {

    private final boolean client;

    public HttpPipelineInitializer(boolean client) {
        this.client = client;
    }

    /**
     * 如果是客户端，则添加HttpResponseDecoder以处理来自服务器的响应
     * @param channel Netty的Channel
     * @throws Exception
     */
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        if (client) {
            // 如果是客户端，则添加HttpResponseDecoder以处理来自服务器的响应
            pipeline.addLast("decoder", new HttpResponseDecoder());
            // 如果是客户端，则添加HttpRequestEncoder以处理来自服务器的响应
            pipeline.addLast("encoder", new HttpRequestEncoder());
        }
        else {
            // 如果是服务器，则添加HttpRequestDecoder以接收来自客户端的请求
            pipeline.addLast("decoder", new HttpRequestDecoder());
            // 如果是服务器，则添加HttpResponseEncoder以向客户端发送响应
            pipeline.addLast("encoder", new HttpResponseEncoder());
        }
    }
}
