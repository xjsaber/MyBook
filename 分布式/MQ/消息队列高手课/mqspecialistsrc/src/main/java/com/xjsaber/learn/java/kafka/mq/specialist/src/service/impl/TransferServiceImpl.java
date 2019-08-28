package com.xjsaber.learn.java.kafka.mq.specialist.src.service.impl;

import com.xjsaber.learn.java.kafka.mq.specialist.src.service.AccountService;
import com.xjsaber.learn.java.kafka.mq.specialist.src.service.TransferService;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * 转账服务的实现
 * @author xjsaber
 */
public class TransferServiceImpl implements TransferService {

    @Resource
    private AccountService accountService;

    @Override
    public CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount) {
        // 异步调用add方法从fromAccount扣除金额
        return accountService.add(fromAccount, -1 * amount)
                .thenCompose(v -> accountService.add(toAccount, amount));
    }
}
