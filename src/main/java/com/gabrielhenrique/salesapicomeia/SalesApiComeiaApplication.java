package com.gabrielhenrique.salesapicomeia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@ComponentScan(basePackages = "com.gabrielhenrique")
@OpenAPIDefinition(
	info = @Info(
		title = "Vendas Comeia",
		description = "Api de vendas para o desafio Java Pleno Comeia 2024",
		version = "1.0"
	)
)
public class SalesApiComeiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApiComeiaApplication.class, args);
	}

}

