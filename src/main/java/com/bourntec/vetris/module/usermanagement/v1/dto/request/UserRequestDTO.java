package com.bourntec.vetris.module.usermanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.bourntec.vetris.entity.User;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Anandu
 *User Request DTO class
 */

@Getter
@Setter
@Validated
public class UserRequestDTO {
	
	@NotNull
	@Size(min = 1, max = 15, message = "First Name  must be atmost 15 characters")
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private Integer age;
	
	@NotNull
	private String country;
	
	@NotNull
	private String city;
	
	private long phoneNumber;
	
	private String emailId;
	
	
	/**
	 * @param requestDTO
	 * @return user
	 */
	public User toModel(UserRequestDTO request) {
		User user= new User();
		try {
			if (request.getAge()!=0) {
				user.setAge(request.getAge());
			}
			if (request.getFirstName()!=null) {
				user.setFirstName(request.getFirstName());
			}
			if (request.getLastName()!=null) {
				user.setLastName(request.getLastName());
			}
			if (request.getCity()!=null) {
				user.setCity(request.getCity());
			}
			if (request.getCountry()!=null) {
				user.setCountry(request.getCountry());
			}
			if (request.getEmailId()!=null) {
				user.setEmailId(request.getEmailId());
			}
			if (request.getPhoneNumber()!=0) {
				user.setPhoneNumber(request.getPhoneNumber());
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}

}
