package com.xjsaber.netty.im.first.protocol;

import com.xjsaber.netty.im.first.protocol.command.Command;
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
