package com.cinematrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.cinematrics.*")
@ComponentScan("com.cinematrics.*")
public class CinematricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinematricsApplication.class, args);
	}

}
