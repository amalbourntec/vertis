package com.vetris.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


/**
 *
 * @author Amal
 *
 */

@EntityScan("com.vetris.entity")
@ComponentScan("com.vetris")
@SpringBootApplication
public class UserManagementApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
	
}
