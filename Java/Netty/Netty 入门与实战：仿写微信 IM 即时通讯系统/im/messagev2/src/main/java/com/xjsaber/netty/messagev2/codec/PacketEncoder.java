package com.xjsaber.netty.messagev2.codec;

import com.xjsaber.netty.messagev2.protocol.Packet;
import com.xjsaber.netty.messagev2.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xjsaber
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        ctx.pipeline().writeAndFlush(PacketCodeC.INSTANCE.encode(out, msg));
    }
}
