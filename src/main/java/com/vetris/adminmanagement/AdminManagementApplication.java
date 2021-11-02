package com.vetris.adminmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 *
 * @author Amal
 *
 */

@EntityScan("com.vetris.entity")
@ComponentScan({"com.vetris.usermanagement","com.vetris.utils"})
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
@EnableJpaAuditing
public class AdminManagementApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(AdminManagementApplication.class, args);
    }
	
}
