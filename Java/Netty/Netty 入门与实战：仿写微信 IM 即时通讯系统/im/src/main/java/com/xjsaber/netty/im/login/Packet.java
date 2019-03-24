package com.xjsaber.netty.im.login;

import lombok.Data;

@Data
public abstract class Packet {

    final Byte LOGIN_REQUEST = 1;

    final Byte MESSAGE_REQUEST = 2;

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }
}
