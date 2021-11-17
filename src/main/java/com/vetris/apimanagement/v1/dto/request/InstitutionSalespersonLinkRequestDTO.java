package com.vetris.apimanagement.v1.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * InstitutionSalespersonLink Request DTO class
 * 
 * @author Aldrin
 */
@Getter
@Setter
@Validated
public class InstitutionSalespersonLinkRequestDTO {

	@Size(max = 200, message = "salesperson id  must be atmost 200 characters")
	@NotBlank(message="Salesperson Id must not be empty")
	private String salespersonId;
	
	@Size(max = 200, message = "institution id  must be atmost 200 characters")
	@NotBlank(message="Institution Id must not be empty")
	private String institutionId;

	@Size(max = 200, message = "salesperson name  must be atmost 200 characters")
	private String salespersonName;

	@Size(max = 80, message = "salesperson first name  must be atmost 80 characters")
	private String salespersonFName;

	@Size(max = 80, message = "salesperson last name  must be atmost 80 characters")
	private String salespersonLName;

	@Size(max = 30, message = "salesperson credentials  must be atmost 30 characters")
	private String salespersonCredentials;

	@Email(message = "Please provide a valid Email")
	@Size(max = 50, message = "salesperson login email  must be atmost 50 characters")
	private String salespersonLoginEmail;
	
	@Email(message = "Please provide a valid Email")
	@Size(max = 50, message = "salesperson email  must be atmost 50 characters")
	private String salespersonEmail;

	@Pattern(regexp = "^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$")
	@Size(max = 50, message = "salesperson mobile  must be atmost 50 characters")
	private String salespersonMobile;

	@Size(max = 200, message = "salesperson user id  must be atmost 200 characters")
	private String salespersonUserId;

	@Size(max = 20, message = "salesperson pacs user id  must be atmost 20 characters")
	private String salespersonPacsUserId;

	@Size(max = 200, message = "salesperson pacs password must be atmost 200 characters")
	private String salespersonPacsPassword;

	
	private Double commission1stYr;

	
	private Double commission2ndYr;

	@Size(max = 200, message = "billing account id  must be atmost 200 characters")
	private String billingAccountId;
}
