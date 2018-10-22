package com.xjsaber.netty.message.protocol.response;

import com.xjsaber.netty.message.protocol.Packet;
import com.xjsaber.netty.message.protocol.command.Command;
import lombok.Data;

/**
 * @author xjsaber
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return Command.MESSAGE_RESPONSE;
    }
}
