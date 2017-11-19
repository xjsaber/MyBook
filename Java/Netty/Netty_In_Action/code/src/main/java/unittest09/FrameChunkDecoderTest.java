package unittest09;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author xjsaber
 */
public class FrameChunkDecoderTest {

    @Test
    public void testFramesDecoded(){

        // 创建一个ByteBuf，并向它写入9字节
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++){
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();

        // 创建一个 EmbeddedChannel，并向其安装一个帧大小为 3 字节的 FixedLengthFrameDecoder
        EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));

        // 向它写入 2 字节，并断言它们将会产生一个新帧
        Assert.assertTrue(channel.writeInbound(input.getByte(2)));
        try {
            // 写入一个 4 字节大小的帧，并捕获预期的TooLongFrameException
            channel.writeInbound(input.readBytes(4));
            // 如果上面没有抛出异常，那么就会到达这个断言，并且测试失败
            Assert.fail();
        } catch (TooLongFrameException e){
            // expected exception
        }

        // 写入剩余的2字节，并断言将会产生一个有效帧
        Assert.assertTrue(channel.writeInbound(input.readBytes(3)));
        Assert.assertTrue(channel.finish());

        // Read frames 读取产生的消息，并且验证值
        ByteBuf read = channel.readInbound();
        Assert.assertEquals(buf.readSlice(2), read);
        read.release();

        read = channel.readInbound();
        Assert.assertEquals(buf.skipBytes(4).readSlice(3), read);
        read.release();
        buf.release();
    }
}
