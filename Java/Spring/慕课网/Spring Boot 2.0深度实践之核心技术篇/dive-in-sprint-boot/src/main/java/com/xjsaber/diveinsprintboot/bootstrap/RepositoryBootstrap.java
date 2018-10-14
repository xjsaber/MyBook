package com.xjsaber.diveinsprintboot.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 仓储的引导类
 */
@ComponentScan(basePackages = "com.xjsaber.diveinsprintboot.annotation")
public class RepositoryBootstrap {

    public static void main(String[] args){
        ConfigurableApplicationContext context = new SpringApplicationBuilder(RepositoryBootstrap.class)
                // 非web类型
                .web(WebApplicationType.NONE)
                .run(args);

        // 关闭上下文
        context.close();
    }
}
