package com.vetris.mastermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Institution Physician Link Response DTO class
 * 
 * @author Jini
 *
 */

@Getter
@Setter
public class InstitutionPhysicianLinkResponseDTO {

	private String physicianId;

	private String institutionId;

	private String physicianName;

	private String physicianFname;

	private String physicianLname;

	private String physicianCredentials;

	private String physicianLoginEmail;

	private String physicianEmail;

	private String physicianMobile;

	private String physicianUserId;

	private String physicianPacsUserId;

	private String physicianPacsPassword;

	private String createdBy;

	private Date dateCreated;

	private String updateBy;

	private Date dateUpdated;

	private String billingAccountId;

}
