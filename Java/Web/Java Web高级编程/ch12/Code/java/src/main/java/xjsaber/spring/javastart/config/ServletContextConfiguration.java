package xjsaber.spring.javastart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by xjSaber on 2017/1/29.
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = "xjsaber.spring.javastart.site",
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(Controller.class)
)
public class ServletContextConfiguration {

}
