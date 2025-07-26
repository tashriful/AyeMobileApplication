package com.aye.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@EntityScan("com.aye.web.model")
@EntityScan(basePackages= {"com.aye.web.model"})
@ComponentScan("com.aye")
public class AyeBackofficeUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AyeBackofficeUiApplication.class, args);
	}

}
