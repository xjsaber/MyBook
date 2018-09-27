package com.xjsaber.netty.im.action;

import com.xjsaber.netty.im.action.entity.Packet;
import com.xjsaber.netty.im.action.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;

    public ByteBuf encode(Packet packet){
        // 1. 创建ByteBuf对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeByte(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(MAGIC_NUMBER);
//        byteBuf.writeBytes();
    }

//    public Packet decode(ByteBuf byteBuf){
//        // 跳过 magic number
//        byteBuf.skipBytes(4);
//
//    }
}
