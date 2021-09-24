package com.bourntec.vetris.module.usermanagement.v1.dto.response;







import lombok.Getter;
import lombok.Setter;

/**
 * @author Amal
 * Response DTO for User
 */
@Getter
@Setter

public class UserResponseDTO {
  
	private String firstName;
	private String lastName;
	private Integer age;
	private String country;
	private String city;
	private long phoneNumber;
	private String emailId;
	private String responseMessage;
	
}
