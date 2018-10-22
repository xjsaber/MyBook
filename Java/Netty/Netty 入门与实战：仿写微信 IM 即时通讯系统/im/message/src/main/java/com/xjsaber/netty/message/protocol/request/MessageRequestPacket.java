package com.xjsaber.netty.message.protocol.request;

import com.xjsaber.netty.message.protocol.Packet;
import lombok.Data;

import static com.xjsaber.netty.message.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author xjsaber
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
