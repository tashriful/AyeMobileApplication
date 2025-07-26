package com.aye.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AyeBaseMobileBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AyeBaseMobileBackendApplication.class, args);
	}

}
