package com.avarsh.hrm;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmApplication.class, args);
	}

	@Bean
	public OpenAPI openApi() {
		Info inform = new Info().description("Employee Management Project").title("Avarsh Technologies").summary("Sample Project for Avarsh Intership").version("sample");
		return new OpenAPI().info(inform);
	}
}

