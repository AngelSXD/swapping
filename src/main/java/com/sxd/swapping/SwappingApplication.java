package com.sxd.swapping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.sxd.swapping.mybatis.dao")
public class SwappingApplication {

	public static void main(String[] args) {
		/**
		 * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
		 * 解决netty冲突后初始化client时还会抛出异常
		 * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
		 */
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication.run(SwappingApplication.class, args);
		System.out.println("SXD-swapping 服务启动成功拉！");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
	}
}
