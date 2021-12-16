package com.vetris.apimanagement.v1.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO for Institution physicain modality.
 * 
 * @author Jose Eldhose
 *
 */
@Getter
@Setter
@Validated
public class InstitutionRegPhysicianLinkRequestDTO {

	@NotBlank(message = "InstirutionId must not be empty")
	private String institutionId;

	@Size(max = 200, message = "physicianName  must be atmost 200 characters")
	private String physicianName;

	@Size(max = 80, message = "physicianFname  must be atmost 80 characters")
	private String physicianFname;

	@Size(max = 80, message = "physicianLname   must be atmost 80 characters")
	private String physicianLname;

	@Size(max = 30, message = "physicianCredentilas   must be atmost 30 characters")
	private String physicianCredentials;

	@Email(message = "Invalid Email Id")
	@Size(max = 500, message = "physicianEmail  must be atmost 500 characters")
	private String physicianEmail;

	@Pattern(regexp = "^\\+\\d*$", message = "Invalid mob Number")
	@Size(max = 13, message = "physicianMobile  must be atmost 13 characters")
	private String physicianMobile;

}
