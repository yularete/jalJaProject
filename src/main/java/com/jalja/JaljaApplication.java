package com.jalja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JaljaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaljaApplication.class, args);
	}

}
