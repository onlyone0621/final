package com.cbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan()
@SpringBootApplication
public class CboApplication {

	public static void main(String[] args) {
		SpringApplication.run(CboApplication.class, args);
	}

}
