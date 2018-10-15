package com.xjsaber.diveinsprintboot.bootstrap;

import com.xjsaber.diveinsprintboot.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.xjsaber.diveinsprintboot.service")
public class CalculateSpringBootstrap{

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateSpringBootstrap.class)
                // 非web类型
                .web(WebApplicationType.NONE)
                .profiles("Java8")
                .run(args);

        CalculateService service = context.getBean(CalculateService.class);
        System.out.println(service.sum(1,2,3,4,5,6,7,8,9,10));

        // 关闭上下文
        context.close();
    }
}
