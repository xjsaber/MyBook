package com.xjsaber.learn.spring.springboot.config;

import com.xjsaber.learn.spring.springboot.condition.DatabaseConditional;
import com.xjsaber.learn.spring.springboot.mybatis.MyBatisUserRepository;
import com.xjsaber.learn.spring.springboot.pojo.User;
import lombok.NoArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * #@ComponentScan 只会扫描config包里的bean
 * @author xjsaber
 */
@ComponentScan
@Configuration
@NoArgsConstructor
public class AppConfig {

    private SqlSessionFactory sqlSessionFactory;

    public AppConfig(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

//    @Bean
//    public MapperFactoryBean<MyBatisUserRepository> initMybatisUserRepository(){
//        MapperFactoryBean<MyBatisUserRepository> bean = new MapperFactoryBean<>();
//        bean.setMapperInterface(MyBatisUserRepository.class);
//        bean.setSqlSessionFactory(sqlSessionFactory);
//        return bean;
//    }

    /**
     * 配置Mybatis接口扫描
     * @return 返回扫描器
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){

        // 定义扫描器实例
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 加载SqlSessionFactory，Spring Boot会自动生产，SqlSessionFactory实例
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        // 定义扫描的包
        mapperScannerConfigurer.setBasePackage("com.xjsaber.learn.spring.springboot.mybatis.*");
        // 限定被标注@Repository的接口才被扫描
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        // 通过继承某个接口限制扫描，一般使用不多
        // mapperScannerConfigurer.setMarkerInterface(......);
        return mapperScannerConfigurer;
    }

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
