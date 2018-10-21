package com.xjsaber.netty.im.login;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
