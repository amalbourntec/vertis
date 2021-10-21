package com.vetris.usermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Aldrin
 * Response DTO for Institution Device Link
 */
@Getter
@Setter
public class InstitutionDeviceLinkResponseDTO {

    private Integer deviceId;
    
	private Integer institutionId;
	
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
