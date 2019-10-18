package com.example.eurekaserviceRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaServiceRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceRegisterApplication.class, args);
	}

}
