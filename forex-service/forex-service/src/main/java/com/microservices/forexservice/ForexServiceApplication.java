package com.microservices.forexservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class ForexServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexServiceApplication.class, args);
	}

}
