package com.xjsaber.learn.java;

import java.math.BigDecimal;

public class Wallet {
    
    private String id;
    private long createTime;
    private BigDecimal balance;
    private long balanceLastModifiedTime;

    public Wallet() {
//        this.id = IdGenerator.get
        this.createTime = System.currentTimeMillis();
        this.balance = BigDecimal.ZERO;
        this.balanceLastModifiedTime = System.currentTimeMillis();
    }

}
