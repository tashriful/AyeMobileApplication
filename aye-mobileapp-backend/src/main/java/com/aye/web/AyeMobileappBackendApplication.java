package com.aye.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AyeMobileappBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AyeMobileappBackendApplication.class, args);
	}

}
