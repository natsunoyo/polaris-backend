package com.jobportal.polaris_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PolarisBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolarisBackendApplication.class, args);
	}

}
