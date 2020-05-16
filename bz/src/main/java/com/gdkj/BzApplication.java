package com.gdkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.gdkj.bz"})
@MapperScan("com.gdkj.bz.dao")
@RestController
public class BzApplication {

	public static void main(String[] args) {
		SpringApplication.run(BzApplication.class, args);
	}

}
