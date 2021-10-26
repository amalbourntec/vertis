package com.vetris.mastermanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

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

	@NotNull
	private String institutionId;

	@Size(max = 200, message = "physicianName  must be atmost 200 characters")
	private String physicianName;

	@Size(max = 80, message = "physicianFname  must be atmost 80 characters")
	private String physicianFname;

	@Size(max = 80, message = "physicianLname   must be atmost 80 characters")
	private String physicianLname;

	@Size(max = 30, message = "physicianCredentilas   must be atmost 30 characters")
	private String physicianCredentials;

	@Size(max = 500, message = "physicianEmail  must be atmost 500 characters")
	private String physicianEmail;

	@Size(max = 500, message = "physicianMobile  must be atmost 500 characters")
	private String physicianMobile;

}
