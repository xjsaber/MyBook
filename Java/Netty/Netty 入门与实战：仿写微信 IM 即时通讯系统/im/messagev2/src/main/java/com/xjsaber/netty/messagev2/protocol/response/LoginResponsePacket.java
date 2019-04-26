package com.xjsaber.netty.messagev2.protocol.response;

import com.xjsaber.netty.messagev2.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.xjsaber.netty.messagev2.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author xjsaber
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
