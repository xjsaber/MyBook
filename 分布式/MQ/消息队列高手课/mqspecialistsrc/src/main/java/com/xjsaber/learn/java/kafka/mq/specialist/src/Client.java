package com.xjsaber.learn.java.kafka.mq.specialist.src;

import com.xjsaber.learn.java.kafka.mq.specialist.src.service.TransferService;

import java.util.concurrent.ExecutionException;

/**
 * @author xjsaber
 */
public class Client {

    private TransferService transferService;
    private final static int A = 1000;
    private final static int B = 1001;

    public void syncInvoke() throws ExecutionException, InterruptedException{
        // 同步调用
        transferService.transfer(A, B, 100).get();
        System.out.println("转账完成");
    }

    public void asyncInvoke() {
        transferService.transfer(A, B, 100)
                .thenRun(() -> System.out.println("转账完成"));
    }
}
