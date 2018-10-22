package com.xjsaber.netty.message.protocol.request;

import lombok.Data;

import com.xjsaber.netty.message.protocol.Packet;
import static com.xjsaber.netty.message.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author xjsaber
 */
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
