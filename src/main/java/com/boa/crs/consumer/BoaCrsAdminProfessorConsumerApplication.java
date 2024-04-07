package com.boa.crs.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan("com.boa.crs.*")
@EnableDiscoveryClient
public class BoaCrsAdminProfessorConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoaCrsAdminProfessorConsumerApplication.class, args);
	}

}
