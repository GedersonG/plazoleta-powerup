package com.pragma.plazoletaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PlazoletaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlazoletaServiceApplication.class, args);
	}

}
