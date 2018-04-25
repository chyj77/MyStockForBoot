package com.cyj.mystock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.cyj.mystock.mapper")
@EnableScheduling
@EnableCaching// 开启缓存，需要显示的指定
public class MystockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MystockApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
