package com.aye.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@ComponentScan(basePackages = "com.aye.web.mobileBase")
public class MobileappAuthserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileappAuthserviceApplication.class, args);
	}

}
