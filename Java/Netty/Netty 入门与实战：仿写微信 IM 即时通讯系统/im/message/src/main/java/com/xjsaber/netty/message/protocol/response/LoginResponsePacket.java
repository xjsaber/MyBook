package com.xjsaber.netty.message.protocol.response;

import com.xjsaber.netty.message.protocol.Packet;
import lombok.Data;

import static com.xjsaber.netty.message.protocol.command.Command.LOGIN_RESPONSE;

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
