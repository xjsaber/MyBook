package com.xjsaber.netty.im.first.protocol;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.xjsaber.netty.im.first.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author xjsaber
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

    public boolean isSuccess(){
        return true;
    }
}


