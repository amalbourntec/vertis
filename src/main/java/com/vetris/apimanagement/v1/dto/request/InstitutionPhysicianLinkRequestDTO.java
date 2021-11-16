package com.vetris.apimanagement.v1.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * Institution Physician Link Request DTO class
 * 
 * @author Jini
 *
 */

@Getter
@Setter
@Validated
public class InstitutionPhysicianLinkRequestDTO {

	@NotBlank(message = "InstirutionId must not be empty")
	private String institutionId;

	@Size(max = 200, message = "physician Name  must be atmost 200 characters")
	private String physicianName;

	@Size(max = 80, message = "physician Fname  must be atmost 80 characters")
	private String physicianFname;

	@Size(max = 80, message = "physician Lname  must be atmost 80 characters")
	private String physicianLname;

	@Size(max = 30, message = "physician Credentials  must be atmost 30 characters")
	private String physicianCredentials;

	@Email(message = "Invalid PhysicianLoginEmail")
	@Size(max = 50, message = "physician Login Email  must be atmost 50 characters")
	private String physicianLoginEmail;

	@Email(message = "Invalid PhysicianEmail")
	@Size(max = 500, message = "physician Email  must be atmost 500 characters")
	private String physicianEmail;

	@Pattern(regexp = "^\\+\\d*$", message = "Invalid PhysicianMobile Number")
	@Size(max = 500, message = "  physician Mobile  must be atmost 500 characters")
	private String physicianMobile;

	private String physicianUserId;

	@Size(max = 10, message = "physician Pacs User Id   must be atmost 10 characters")
	private String physicianPacsUserId;

	@Size(max = 200, message = "physician Pacs Password  must be atmost 200 characters")
	private String physicianPacsPassword;

	private String billingAccountId;

}
