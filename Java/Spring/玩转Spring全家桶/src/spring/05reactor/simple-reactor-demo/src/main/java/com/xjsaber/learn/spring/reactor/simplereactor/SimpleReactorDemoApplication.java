package com.xjsaber.learn.spring.reactor.simplereactor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class SimpleReactorDemoApplication implements ApplicationRunner {


    public static void main(String[] args){
        SpringApplication.run(SimpleReactorDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1-6的序列
        Flux.range(1, 6)
                .doOnRequest(n -> log.info("Request {} number", n))
                .doOnComplete(() -> log.info("Publish COMPLETE 1"))
                .map(i -> {
                    log.info("Publish {}, {}", Thread.currentThread(), i);
//                    return 10 / (i - 3);
                    return i;
                })
                .doOnComplete(() -> log.info("Publish COMPLETE 2"))
                .subscribe(i -> log.info("Subscribe {}: {}", Thread.currentThread(), i),
                        e -> log.error("error {}", e.toString()),
                        () -> log.info("Subscribe COMPLETE"));
        Thread.sleep(2000);
    }

}
