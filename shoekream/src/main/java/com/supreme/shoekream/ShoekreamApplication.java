package com.supreme.shoekream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ShoekreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoekreamApplication.class, args);
	}

}
