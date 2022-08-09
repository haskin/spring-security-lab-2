package dev.haskin.springsecuritylab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * Problems:
 * 	x Update your "bitcoin" endpoint so that it can take the name of the
   		cryptocurrency to get information about in its request.
	- Update all your tests accordingly to ensure that the currency you're getting
   		information about is the currency that was requested.
	- Add `httpBasic()` authentication to your new endpoint.
	- Update your tests accordingly. 
 */
@SpringBootApplication
public class Springsecuritylab1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springsecuritylab1Application.class, args);
	}

	@Bean
	RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

}
