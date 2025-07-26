package com.aye.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AyeMobiledelvschdApplication {

	public static void main(String[] args) {
		SpringApplication.run(AyeMobiledelvschdApplication.class, args);
	}

}
