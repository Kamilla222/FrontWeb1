package com.mynews.frontweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = "com.mynews")
@EnableJpaRepositories(basePackages = "com.mynews.frontweb.repository")
@EntityScan(basePackages = "com.mynews.frontweb.model")
public class FrontWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontWebApplication.class, args);
	}

}
