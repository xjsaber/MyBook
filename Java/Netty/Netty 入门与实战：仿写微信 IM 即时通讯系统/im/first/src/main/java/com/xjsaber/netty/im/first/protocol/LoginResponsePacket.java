package com.xjsaber.netty.im.first.protocol;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.xjsaber.netty.im.first.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author xjsaber
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet{

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
