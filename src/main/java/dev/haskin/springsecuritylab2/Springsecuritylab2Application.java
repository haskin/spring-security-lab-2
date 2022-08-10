package dev.haskin.springsecuritylab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * Problems:
 */
@SpringBootApplication
public class Springsecuritylab2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springsecuritylab2Application.class, args);
	}

	@Bean
	RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

}
