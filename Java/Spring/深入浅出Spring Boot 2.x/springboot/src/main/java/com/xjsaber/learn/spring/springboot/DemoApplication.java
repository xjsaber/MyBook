package com.xjsaber.learn.spring.springboot;

import com.xjsaber.learn.spring.springboot.aspect.MyAspect;
import com.xjsaber.learn.spring.springboot.mybatis.MyPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 *
 * #@EnableJpaRepositories 定义JPA接口扫描包路径
 * #@EntityScan 定义实体Bean扫描包路径
 * #@MapperScan
 * @author xjsaber
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.xjsaber.learn.spring.springboot.dao")
@EntityScan(basePackages = "com.xjsaber.learn.spring.springboot.pojo")
@MapperScan(
		// 指定扫描包
		basePackages = "com.xjsaber.learn.spring.springboot.*",
		// 指定 sqlSessionFactory，如果sqlSessionTemplate则指定，废弃
		sqlSessionFactoryRef = "sqlSessionFactory",
		// 指定 sqlSessionTemplate，将忽略sqlSessionFactory的配置
		sqlSessionTemplateRef = "sqlSessionTemplate",
		// marketInterface = Class.class, //限定扫描接口，不常用
		annotationClass = Repository.class
)
public class DemoApplication {

	/**
	 * SqlSessionFactory对象由SpringBoot自动装配生成
	 */
	private SqlSessionFactory sqlSessionFactory;

	public DemoApplication(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 启用Spring Bean生命周期执行方法，加入插件
	 */
	@PostConstruct
	public void initMyBatis(){
		// 插件实例
		Interceptor plugin = new MyPlugin();
		// 设置插件属性
		Properties properties = new Properties();
		properties.setProperty("key1", "value1");
		properties.setProperty("key2", "value2");
		properties.setProperty("key3", "value3");
		plugin.setProperties(properties);
		// 在sqlSessionFactory中添加插件
		sqlSessionFactory.getConfiguration().addInterceptor(plugin);
	}

	@Bean
	public MyAspect initMyAspect(){
		return new MyAspect();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
