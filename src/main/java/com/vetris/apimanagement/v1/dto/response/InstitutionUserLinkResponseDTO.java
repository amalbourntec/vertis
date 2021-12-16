package com.vetris.apimanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/*
 * @author Dhanesh C P
 * class for InstitutionUserLinkResponseDTO
 * */
@Getter
@Setter
public class InstitutionUserLinkResponseDTO {
//	private String id;
	private String userId;

	private String institutionId;

	private String userLoginId;

	private String userPwd;

	private String userPacsUserId;

	private String userPacsPassword;

	private String userEmail;

	private String grantedRightsPacs;

	private String updatedInPacs;

	private Date dateUpdatedInPacs;

	private String userContactNo;

	private String createdBy;

	private Date dateCreated;

	private String updateBy;

	private Date dateUpdated;
}
