package com.ssafy.ws;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.ssafy.ws.model.repo")
@EnableSwagger2
public class WsSpring06Gumi0607thLeegideokApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsSpring06Gumi0607thLeegideokApplication.class, args);
	}

}
