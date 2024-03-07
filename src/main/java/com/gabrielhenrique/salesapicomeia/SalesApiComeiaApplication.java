package com.gabrielhenrique.salesapicomeia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.gabrielhenrique")
public class SalesApiComeiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApiComeiaApplication.class, args);
	}

}

