package websocket12;

import io.netty.buffer.ByteBuf;

/**
 * 声明WebSocketConvertHandler
 * @author xjsaber
 */
public class MyWebSocketFrame {
    // 定义拥有被包装的有效负载的WebSocketFrame的类型
    public enum FrameType {
        BINARY,
        CLOSE,
        PING,
        PONG,
        TEXT,
        CONTINUATION
    }
    private final FrameType type;
    private final ByteBuf data;

    public MyWebSocketFrame(FrameType type, ByteBuf data) {
        this.type = type;
        this.data = data;
    }

    public FrameType getType() {
        return type;
    }

    public ByteBuf getData() {
        return data;
    }
}
