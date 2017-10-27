package websocket12;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.*;

import java.util.List;

/**
 * @author xjsaber
 */
public class WebSocketConvertHandler extends MessageToMessageCodec<WebSocketFrame, MyWebSocketFrame> {

    /**
     * 将MyWebSocketFrame编码为指定的
     * @param ctx 上下文
     * @param msg websocket传输过来的内容
     * @param out 输出内容
     * @throws Exception IllegalStateException
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, MyWebSocketFrame msg, List<Object> out) throws Exception {

    }

    /**
     * 将WebSocketFrame解码为MyWebSocketFrame，并设置FrameType
     * @param ctx 上下文
     * @param msg websocket传输过来的内容
     * @param out 输出内容
     * @throws Exception IllegalStateException
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
        ByteBuf payload = msg.content().duplicate().retain();
        if (msg instanceof BinaryWebSocketFrame) {
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.BINARY, payload));
        }
        else if (msg instanceof CloseWebSocketFrame) {
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.CLOSE, payload));
        }
        else if (msg instanceof PingWebSocketFrame) {
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.PING, payload));
        }
        else if (msg instanceof PongWebSocketFrame) {
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.PONG, payload));
        }
        else if (msg instanceof TextWebSocketFrame) {
            out.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.TEXT, payload));
        }
        else {
            throw new IllegalStateException("Unsupported websocket msg " + msg);
        }
    }

}
