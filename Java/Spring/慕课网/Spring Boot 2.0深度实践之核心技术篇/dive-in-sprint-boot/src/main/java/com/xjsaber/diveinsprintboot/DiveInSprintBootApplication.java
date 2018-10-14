package com.xjsaber.diveinsprintboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.xjsaber.diveinsprintboot.web.servlet")
public class DiveInSprintBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiveInSprintBootApplication.class, args);
	}
}
