package com.vetris.usermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO for Physician
 * 
 * @author Jose Eldhose
 *
 */
@Getter
@Setter
public class InstitutionRegPhysicianLinkResponseDTO {
	private String physicianId;
	private String institutionId;
	private String physicianName;
	private String physicianFname;
	private String physicianLname;
	private String physicianCredentials;
	private Date dateUpdated;
	private String updateBy;
	private String physicianEmail;
	private String physicianMobile;

}
