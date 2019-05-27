package com.xjsaber.learn.spring.springboot.config;

import com.xjsaber.learn.spring.springboot.condition.DatabaseConditional;
import com.xjsaber.learn.spring.springboot.pojo.User;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * #@ComponentScan 只会扫描config包里的bean
 * @author xjsaber
 */
@ComponentScan
@Configuration
public class AppConfig {

    @Bean
    public User initUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user_name");
        user.setNote("测试路过");
        return user;
    }



    /**
     * 通过Bean定义了配置项name为“dataSource”，那么Spring就会返回的对象用户名称为
     * “dataSource”保存到IoC容器中
     * #@Bean(name = "dataSource", destroyMethod = "close") destoryMethod=close
     */
    @Bean(name = "dataSource")
    @Conditional(DatabaseConditional.class)
    public DataSource getDataSource(){
        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://120.55.56.16/groupon");
        properties.setProperty("username", "root");
        properties.setProperty("password", "12345678");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
