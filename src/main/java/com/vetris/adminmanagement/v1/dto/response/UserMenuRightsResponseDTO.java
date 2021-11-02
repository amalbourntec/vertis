package com.vetris.adminmanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anandu
 *
 */
@Getter
@Setter
public class UserMenuRightsResponseDTO {
  
	private String userId;
	private Integer menuId;
	private String createdBy;
	private Date dateCreated;
	private String updateBy;
	private Date dateUpdated;
	
}
