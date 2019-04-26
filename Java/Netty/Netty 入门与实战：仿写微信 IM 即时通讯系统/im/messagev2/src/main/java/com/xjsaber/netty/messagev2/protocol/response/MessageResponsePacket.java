package com.xjsaber.netty.messagev2.protocol.response;

import com.xjsaber.netty.messagev2.protocol.Packet;
import com.xjsaber.netty.messagev2.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xjsaber
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {

        return Command.MESSAGE_RESPONSE;
    }
}
