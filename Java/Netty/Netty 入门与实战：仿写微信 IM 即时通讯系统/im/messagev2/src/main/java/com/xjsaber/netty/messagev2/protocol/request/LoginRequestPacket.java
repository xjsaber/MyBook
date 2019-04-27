package com.xjsaber.netty.messagev2.protocol.request;

import com.xjsaber.netty.messagev2.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.xjsaber.netty.messagev2.protocol.command.Command.LOGIN_REQUEST;

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
