package com.vetris.apimanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO for InstitutionSalespersonLink
 * 
 * @author Aldrin
 */
@Getter
@Setter
public class InstitutionSalespersonLinkResponseDTO {
	
	private String salespersonId;
	
	private String institutionId;

	private String salespersonName;

	private String salespersonFName;

	private String salespersonLName;

	private String salespersonCredentials;

	private String salespersonLoginEmail;

	private String salespersonEmail;

	private String salespersonMobile;

	private String salespersonUserId;

	private String salespersonPacsUserId;

	private String salespersonPacsPassword;

	private String createdBy;

	private Date dateCreated;

	private String updateBy;

	private Date dateUpdated;

	private Double commission1stYr;

	private Double commission2ndYr;

	private String billingAccountId;
}
