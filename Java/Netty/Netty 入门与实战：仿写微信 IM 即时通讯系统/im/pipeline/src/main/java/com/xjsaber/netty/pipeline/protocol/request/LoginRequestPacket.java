package com.xjsaber.netty.pipeline.protocol.request;

import com.xjsaber.netty.pipeline.protocol.Packet;
import lombok.Data;

import static com.xjsaber.netty.pipeline.protocol.command.Command.LOGIN_REQUEST;

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
