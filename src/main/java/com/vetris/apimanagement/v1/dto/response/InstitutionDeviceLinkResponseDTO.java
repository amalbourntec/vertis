package com.vetris.apimanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO for Institution Device Link
 * 
 * @author Aldrin
 */
@Getter
@Setter
public class InstitutionDeviceLinkResponseDTO {

 
	private String deviceId;
	private String institutionId;

	private String manufacturer;

	private String model;

	private String serialNo;

	private String createdBy;

	private Date dateCreated;

	private String updateBy;

	private Date dateUpdated;

	private String modalityAeTitle;

	private String modality;

	private String weightUom;
}
