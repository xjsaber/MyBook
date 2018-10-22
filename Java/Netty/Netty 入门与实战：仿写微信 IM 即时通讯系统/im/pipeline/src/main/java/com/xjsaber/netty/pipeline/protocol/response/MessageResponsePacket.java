package com.xjsaber.netty.pipeline.protocol.response;

import com.xjsaber.netty.pipeline.protocol.Packet;
import com.xjsaber.netty.pipeline.protocol.command.Command;
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
