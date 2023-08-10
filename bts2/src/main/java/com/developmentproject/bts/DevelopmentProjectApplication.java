package com.developmentproject.bts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.developmentproject.bts.entity")
public class DevelopmentProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevelopmentProjectApplication.class, args);
	}

}

