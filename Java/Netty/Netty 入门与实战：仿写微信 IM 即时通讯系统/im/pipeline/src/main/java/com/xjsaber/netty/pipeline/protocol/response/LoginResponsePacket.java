package com.xjsaber.netty.pipeline.protocol.response;

import com.xjsaber.netty.pipeline.protocol.Packet;
import lombok.Data;

import static com.xjsaber.netty.pipeline.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author xjsaber
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
