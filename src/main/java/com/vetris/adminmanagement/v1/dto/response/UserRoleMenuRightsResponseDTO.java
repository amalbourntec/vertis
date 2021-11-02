package com.vetris.adminmanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anandu
 *
 */
@Setter
@Getter
public class UserRoleMenuRightsResponseDTO {

	private Integer userRoleId;
	private Integer menuId;
	private String createdBy;
	private Date dateCreated;
	private String updateBy;
	private Date dateUpdated;
	
}
