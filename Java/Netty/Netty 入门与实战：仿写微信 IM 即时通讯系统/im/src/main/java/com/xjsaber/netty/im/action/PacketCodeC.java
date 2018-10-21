package com.xjsaber.netty.im.action;

import com.xjsaber.netty.im.login.LoginRequestPacket;
import com.xjsaber.netty.im.login.Packet;
import com.xjsaber.netty.im.action.serialize.JSONSerializer;
import com.xjsaber.netty.im.action.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.xjsaber.netty.im.action.Command.LOGIN_REQUEST;

@SuppressWarnings("Duplicates")
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;


    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }

    public ByteBuf encode(Packet packet){
        // 1. 创建ByteBuf对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 2. 序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null){
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm){

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command){

        return packetTypeMap.get(command);
    }
}
