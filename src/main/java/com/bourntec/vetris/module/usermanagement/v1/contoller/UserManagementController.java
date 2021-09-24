package com.bourntec.vetris.module.usermanagement.v1.contoller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for UserManagement
 * @author Amal
 *
 */

@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usermanagement/v1/user")
public class UserManagementController {
	
	
	@GetMapping("")
	public String fetchUser() throws Exception {

		System.out.println("hello world");
		return "hello World";
		
	}

}
