package com.vetris.mastermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Aldrin Response DTO for InstitutionAltNameLink
 */
@Getter
@Setter
public class InstitutionAltNameLinkResponseDTO {

	private String institutionId;

	private String alternateName;

	private String createdBy;

	private Date dateCreated;

	private String updateBy;

	private Date dateUpdated;
}
