package com.xjsaber.netty.im.first.protocol;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.xjsaber.netty.im.first.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author xjsaber
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
