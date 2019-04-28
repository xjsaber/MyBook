package com.xjsaber.netty.im.first.codec;

import com.xjsaber.netty.im.first.protocol.Packet;
import com.xjsaber.netty.im.first.protocol.PacketCodeC;
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
