package com.bourntec.vetris.module.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


/**
 *
 * @author Amal
 *
 */

@EntityScan("com.bourntec.vetris.entity")
@ComponentScan("com.bourntec")
@SpringBootApplication
public class UserManagementApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
	
}
