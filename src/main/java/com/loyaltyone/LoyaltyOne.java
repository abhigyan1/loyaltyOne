package com.loyaltyone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication (exclude = {JacksonAutoConfiguration.class})
@EnableJpaAuditing

public class LoyaltyOne  {
	
	public static void main(String[] args) {
		SpringApplication.run(LoyaltyOne.class, args);
		
	}
	
	
}
