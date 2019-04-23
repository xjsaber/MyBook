package com.xjsaber.netty.im.login.protocol;

import lombok.Data;

import static com.xjsaber.netty.im.login.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author xjsaber
 */
@Data
public class LoginRequestPacket extends Packet {
    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}


