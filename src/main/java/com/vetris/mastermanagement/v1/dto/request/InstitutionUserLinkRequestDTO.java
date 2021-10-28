package com.vetris.mastermanagement.v1.dto.request;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class InstitutionUserLinkRequestDTO {
	
	
	private String userId;
	
	private String institutionId;
	
	@Size(max = 50, message = "userLoginId  must be atmost 50 characters")
	private String userLoginId;
	
	@Size(max = 200, message = "userPwd  must be atmost 200 characters")
	private String userPwd;
	
	@Size(max = 20, message = "userPacsUserId  must be atmost 20 characters")
	private String userPacsUserId;
	
	@Size(max = 200, message = "userPacsPassword  must be atmost 200 characters")
	private String userPacsPassword;
	
	@Size(max = 50, message = "userEmail  must be atmost 50 characters")
	private String userEmail;
	
	@Size(max = 30, message = "grantedRightsPacs  must be atmost 30 characters")
	private String grantedRightsPacs;

	@Size(max = 1, message = "updatedInPacs  must be atmost 1 characters")
	private Character updatedInPacs;
	
	private Date dateUpdatedInPacs;
	
	@Size(max = 20, message = "userContactNo  must be atmost 20 characters")
	private String userContactNo;
}
