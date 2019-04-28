package com.xjsaber.netty.im.first.protocol;

import lombok.Data;

/**
 * @author xjsaber
 */
@Data
public abstract class Packet {

    /**
     * 协议成本
     */
    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();
}
