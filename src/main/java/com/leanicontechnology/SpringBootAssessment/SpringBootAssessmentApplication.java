package com.leanicontechnology.SpringBootAssessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringBootAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAssessmentApplication.class, args);
	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
	 return new WebMvcConfigurer() {
		 @Override
		 public void addCorsMappings(CorsRegistry registry) {
			 registry.addMapping("/**")
					 .allowedOrigins("http://localhost:4000")
					 .allowedMethods("GET", "POST", "PUT", "DELETE");
		 }
	 };
	}

}
