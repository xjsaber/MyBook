package com.xjsaber.learn.java.kafka.mq.specialist.src.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 转账服务
 * @author xjsaber
 */
@Service
public interface TransferService {

    /**
     * @param fromAccount 转出帐号
     * @param toAccount 转入帐号
     * @param amount 金额
     */
    CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount);
}
