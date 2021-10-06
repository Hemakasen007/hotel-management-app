package com.travel.lodge.financeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PGModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PGModuleApplication.class, args);
	}

}
