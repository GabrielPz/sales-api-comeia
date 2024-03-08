package com.gabrielhenrique.salesapicomeia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@ComponentScan(basePackages = "com.gabrielhenrique")
@OpenAPIDefinition(
	info = @Info(
		title = "Vendas Comeia",
		description = "Api de vendas para o desafio Java Pleno Comeia 2024",
		version = "1.0"
	)
)
@SecurityScheme(name = "jwt_auth", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class SalesApiComeiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesApiComeiaApplication.class, args);
	}

}

