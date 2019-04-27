package com.xjsaber.netty.messagev2.protocol.request;

import com.xjsaber.netty.messagev2.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.xjsaber.netty.messagev2.protocol.command.Command.MESSAGE_REQUEST;

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
