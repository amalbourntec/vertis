package com.vetris.adminmanagement.v1.dto.request;


import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Anandu
 *User Request DTO class
 */

@Getter
@Setter
@Validated
public class UserRequestDTO {
	

	
	@Size( max = 40, message = "code  must be atmost 40 characters")
	private String code;
	
	@Size( max = 200, message = "name  must be atmost 200 characters")
	private String name;
	
	@Size( max = 200, message = "password  must be atmost 200 characters")
	@NotNull
	private String password;

	@Size( max = 100, message = "email id  must be atmost 100 characters")
	private String emailId;

	@Size( max = 20, message = "contact no  must be atmost 20 characters")
	private String contactNo;
	
	@NotNull
	private Integer userRoleId;
	
	private String firstLogin;
	
	@Size( max = 50, message = "pacs_user_id  must be atmost 50 characters")
	private String pacsUserId;

	@Size( max = 200, message = "pacs_password  must be atmost 200 characters")
	@NotNull
	private String pacsPassword;

	@Size( max = 1, message = "is active  must be atmost 1 characters")
	private String isActive;
	
	@Size( max = 1, message = "is visible  must be atmost 1 characters")
	private String isVisible;

	@Size( max = 50, message = "login id  must be atmost 50 characters")
	private String loginId;
	
	@Size( max = 1, message = "notfication_pref  must be atmost 1 characters")
	private String notificationPref;

	@Size( max = 1, message = "allow_manual_submission  must be atmost 1 characters")
	private String allowManualSubmission;
	
	@Size( max = 1, message = "allow_dashboard_view  must be atmost 1 characters")
	private String allowDashboardView;
	
	@Size( max = 10, message = "theme_pref  must be atmost 10 characters")
	private String themePref;

}
