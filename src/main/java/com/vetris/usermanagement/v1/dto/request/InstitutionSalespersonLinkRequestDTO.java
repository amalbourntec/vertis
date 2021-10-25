package com.vetris.usermanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

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

	@Size(max = 200, message = "institution id  must be atmost 200 characters")
	@NotNull
	private String institutionId;

	@Size(max = 200, message = "salesperson name  must be atmost 200 characters")
	private String salespersonName;

	@Size(max = 80, message = "salesperson first name  must be atmost 80 characters")
	private String salespersonFName;

	@Size(max = 80, message = "salesperson last name  must be atmost 80 characters")
	private String salespersonLName;

	@Size(max = 30, message = "salesperson credentials  must be atmost 30 characters")
	private String salespersonCredentials;

	@Size(max = 50, message = "salesperson login email  must be atmost 50 characters")
	private String salespersonLoginEmail;

	@Size(max = 50, message = "salesperson email  must be atmost 50 characters")
	private String salespersonEmail;

	@Size(max = 50, message = "salesperson mobile  must be atmost 50 characters")
	private String salespersonMobile;

	@Size(max = 200, message = "salesperson user id  must be atmost 200 characters")
	private String salespersonUserId;

	@Size(max = 20, message = "salesperson pacs user id  must be atmost 20 characters")
	private String salespersonPacsUserId;

	@Size(max = 200, message = "salesperson pacs password must be atmost 200 characters")
	private String salespersonPacsPassword;

	@Size(max = 30, message = "commission_1st_yr  must be atmost 30 double value")
	private Double commission1stYr;

	@Size(max = 30, message = "commission_2nd_yr  must be atmost 30 double value")
	private Double commission2ndYr;

	@Size(max = 200, message = "billing account id  must be atmost 200 characters")
	private String billingAccountId;
}
