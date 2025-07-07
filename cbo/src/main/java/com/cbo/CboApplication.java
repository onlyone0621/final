package com.cbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;



@MapperScan(basePackages = "com.cbo.mapper")
@SpringBootApplication
public class CboApplication {

	public static void main(String[] args) {
		SpringApplication.run(CboApplication.class, args);
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/profileImage/**")
			.addResourceLocations("file:///C:/upload/profileImage/");
	}
}
