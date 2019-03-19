package com.bbrestconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.bbrestconsumer"})
public class Application {

    /*@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }*/

    //https://github.com/shevabam/breaking-bad-quotes

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
