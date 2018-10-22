package com.xjsaber.netty.pipeline.protocol.request;

import com.xjsaber.netty.pipeline.protocol.Packet;
import lombok.Data;

import static com.xjsaber.netty.pipeline.protocol.command.Command.MESSAGE_REQUEST;

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
