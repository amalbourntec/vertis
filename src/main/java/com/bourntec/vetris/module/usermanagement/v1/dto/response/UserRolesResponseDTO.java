package com.bourntec.vetris.module.usermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Jose Eldhose
 * Response DTO for User Role
 */
@Getter
@Setter
public class UserRolesResponseDTO {

	private Integer id;
	private String code;
	private String name;
	private String createdBy;
	private String isVisible;
	private String isActive;
	private String updateBy;
	private Date   dateUpdated;
	private Date   dateCreated;
	private String sysDefined;
	private String responseMessage;
	
}
