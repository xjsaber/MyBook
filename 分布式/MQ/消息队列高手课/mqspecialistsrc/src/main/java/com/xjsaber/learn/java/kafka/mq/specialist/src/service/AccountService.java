package com.xjsaber.learn.java.kafka.mq.specialist.src.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 账户服务
 * @author xjsaber
 */
@Service
public interface AccountService {

    /**
     * 变更账户金额
     * @param account 账户ID
     * @param amount 增加的金额，负值为减少
     */
    CompletableFuture<Void> add(int account, int amount);
}
