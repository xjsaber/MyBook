package com.xjsaber.netty.im.login.protocol;

import lombok.Data;

import static com.xjsaber.netty.im.login.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author xjsaber
 */
@Data
public class LoginResponsePacket extends Packet{

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
