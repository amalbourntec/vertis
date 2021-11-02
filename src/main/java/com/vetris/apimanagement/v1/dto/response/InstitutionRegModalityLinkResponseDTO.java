package com.vetris.apimanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO for Institution registration modality.
 * 
 * @author Jose Eldhose
 *
 */
@Getter
@Setter
public class InstitutionRegModalityLinkResponseDTO {


	private String modalityId;

	private String institutionId;

	private String updateBy;

	private Date dateUpdated;

}
