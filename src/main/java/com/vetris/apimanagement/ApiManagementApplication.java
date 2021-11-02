package com.vetris.apimanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EntityScan("com.vetris.entity")
@ComponentScan({"com.vetris.apimanagement","com.vetris.utils"})
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableJpaAuditing
public class ApiManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiManagementApplication.class, args);
	}
}  
