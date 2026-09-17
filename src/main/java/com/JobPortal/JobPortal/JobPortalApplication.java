package com.JobPortal.JobPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/// @Springboot is a combination of 3 annotation ->
///      @configuration,   @enableAutoConfiguration,   @ComponentScan
public class JobPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApplication.class, args);
	}

}
