package com.vetris.adminmanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Amal
 * Response DTO for User
 */
@Getter
@Setter

public class UserResponseDTO{
  
	private String code;
	private String name;
	private String emailId;
	private String contactNo;
	private Integer userRoleId;
	private String firstLogin;
	private String pacsUserId;
	private String createdBy;
	private Date dateCreated;
	private String updateBy;
	private Date dateUpdated;
	private String isActive;
	private String isVisible;
	private String loginId;
	private String notificationPref;
	private String allowManualSubmission;
	private String allowDashboardView;
	private String themePref;
}
