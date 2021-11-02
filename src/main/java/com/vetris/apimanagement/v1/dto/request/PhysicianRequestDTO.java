package com.vetris.apimanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anandu
 *
 */
@Getter
@Setter
@Validated
public class PhysicianRequestDTO {
		
	
	@Size(max = 10, message = "code  must be atmost 10 characters")
	private String code;
	
	@Size(max = 80, message = "fname  must be atmost 80 characters")
	private String fname;

	@Size(max = 80, message = "lname  must be atmost 80 characters")
	private String lname;

	@Size(max = 30, message = "credentials  must be atmost 30 characters")
	private String credentials;
	
	@NotNull
	@Size(max = 200, message = "name  must be atmost 200 characters")
	private String name;

	@Size(max = 100, message = "address1  must be atmost 100 characters")
	private String address1;

	@Size(max = 100, message = "address2  must be atmost 100 characters")
	private String address2;

	@Size(max = 100, message = "city  must be atmost 100 characters")
	private String city;
	
	private Integer stateId;

	private Integer countryId;

	@Size(max = 20, message = "zip  must be atmost 20 characters")
	private String zip;

	@Size(max = 500, message = "emailId  must be atmost 500 characters")
	private String emailId;
	
	@Size(max = 30, message = "phoneNo  must be atmost 30 characters")
	private String phoneNo;
	
	@Size(max = 500, message = "mobileNo  must be atmost 500 characters")
	private String mobileNo;
	
	@Size(max = 1, message = "isActive  must be atmost 1 characters")
	private String isActive;
	
	private String institutionId;
	
}
