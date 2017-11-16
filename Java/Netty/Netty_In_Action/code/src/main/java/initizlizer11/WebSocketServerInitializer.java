package initizlizer11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * @author xjsaber
 */
public class WebSocketServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(
                new HttpServerCodec(),
                // 为握手提供聚合的HttpRequest
                new HttpObjectAggregator(65536),
                // 如果被请求的端点是“/websocket”，则处理该升级握手
                new WebSocketServerProtocolHandler("/websocket12"),
                // TextFrameHandler处理TextWebSocketFrame
                new TextFrameHandler(),
                // BinaryFrameHandler处理BinaryWebSocketFrame
                new BinaryFrameHandler(),
                // ContinuationFrameHandler处理ContinuationWebSocketFrame
                new ContinuationFrameHandler()
        );
    }

    public static final class TextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
            // Handle text frame
        }
    }

    public static final class BinaryFrameHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
            // Handle binary frame
        }
    }

    public static final class ContinuationFrameHandler extends SimpleChannelInboundHandler<ContinuationFrameHandler> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ContinuationFrameHandler msg) throws Exception {
            // Handle continuation frame
        }
    }


}
