package xjsaber.spring.javastart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * Created by xjSaber on 2017/1/29.
 */
@Configuration
@ComponentScan(
        basePackages = "xjsaber.spring.javastart.site",
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
public class RootContextConfiguration {
}
