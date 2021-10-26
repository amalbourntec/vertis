package com.vetris.mastermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
*  Institution Category Link Response DTO class
* @author Jini
*
*/

@Getter
@Setter
public class InstitutionCategoryLinkResponseDTO {

	private Integer categoryId;
	private String institutionId;
	private String createdBy;
	private Date dateCreated;

}
