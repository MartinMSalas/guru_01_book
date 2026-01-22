package com.ingenieriasalas.spring_7_webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Spring7WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring7WebappApplication.class, args);
		
	}

}
