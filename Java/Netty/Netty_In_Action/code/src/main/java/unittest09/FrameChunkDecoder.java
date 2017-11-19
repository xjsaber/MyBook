package unittest09;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * 扩展 ByteToMessageDecoder 以将入站字节解码为消息
 * @author xjsaber
 */
public class FrameChunkDecoder extends ByteToMessageDecoder{

    private final int maxFrameSize;

    public FrameChunkDecoder(int maxFrameSize) {
        // 指定将要产生的帧的最大允许大小
        this.maxFrameSize = maxFrameSize;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int readableBytes = in.readableBytes();
        if (readableBytes >= maxFrameSize){
            // discard the bytes 如果该帧太大，则丢弃它并抛出一个 TooLongFrameException……
            in.clear();
            throw new TooLongFrameException();
        }
        // ……否则，从 ByteBuf 中读取一个新的帧
        ByteBuf buf = in.readBytes(maxFrameSize);
        // 将该帧添加到解码消息的 List 中
        out.add(buf);
    }
}
