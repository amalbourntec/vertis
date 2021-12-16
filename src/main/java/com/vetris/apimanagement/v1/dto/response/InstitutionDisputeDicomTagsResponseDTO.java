package com.vetris.apimanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dhanesh InstitutionDisputeDicomTagsResponseDTO class
 */
@Getter
@Setter

public class InstitutionDisputeDicomTagsResponseDTO {

	
	private String institutionId;

	private String groupId;

	private String elementId;

	private String defaultValue;

	private String junkCharacters;

	private String createdBy;

	private Date dateCreated;

	private String updateBy;

	private Date dateUpdated;
}
