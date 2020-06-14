package com.xjsaber.learn.spring.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author xjsaber
 */
@SpringBootApplication
@Slf4j
public class DruidDemoApplication implements CommandLineRunner {

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(DruidDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		log.info(dataSource.toString());
	}
}
