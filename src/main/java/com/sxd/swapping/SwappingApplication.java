package com.sxd.swapping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.sxd.swapping.dao.mybatis")
public class SwappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwappingApplication.class, args);
	}
}
