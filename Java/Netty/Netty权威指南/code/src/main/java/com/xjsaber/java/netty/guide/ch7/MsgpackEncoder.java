package com.xjsaber.java.netty.guide.ch7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @author xjsaber
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object o, ByteBuf out) throws Exception {
        MessagePack msgPack = new MessagePack();
        // Serialize
        byte[] raw = msgPack.write(o);
        out.writeBytes(raw);
    }
}
