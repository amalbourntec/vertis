package com.bourntec.vetris.module.usermanagement.v1.dto.request;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.bourntec.vetris.entity.User;
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
	
	@Size( max = 200, message = "created by  must be atmost 200 characters")
	private String createdBy;

	@NotNull
	private Date dateCreated;
	
	private String updateBy;

	private Date dateUpdated;

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

	
	
	/**
	 * @param requestDTO
	 * @return user
	 */
	public User toModel(UserRequestDTO request) {
		User user= new User();
		try {
			if (request.getCode()!=null) {user.setCode(request.getCode());}
			if (request.getName()!=null) {user.setName(request.getName());}
			if (request.getPassword()!=null ) {user.setPassword(request.getPassword());}
			if (request.getEmailId()!=null) {user.setEmailId(request.getEmailId());}
			if (request.getContactNo()!=null) {user.setContactNo(request.getContactNo());}
			if (request.getUserRoleId()!=null) {user.setUserRoleId(request.getUserRoleId());}
			if (request.getFirstLogin()!=null) {user.setFirstLogin(request.getFirstLogin());}
			if (request.getPacsUserId()!=null) {user.setPacsUserId(request.getPacsUserId());}
			if (request.getPacsPassword()!=null) {user.setPacsPassword(request.getPacsPassword());}
			if (request.getCreatedBy()!=null) {user.setCreatedBy(request.getCreatedBy());}
			if (request.getDateCreated()!=null) {user.setDateCreated(request.getDateCreated());}
			if (request.getUpdateBy()!=null) {user.setUpdateBy(request.getUpdateBy());}
			if (request.getDateCreated()!=null) {user.setDateCreated(request.getDateCreated());}
			if (request.getIsActive()!=null) {user.setIsActive(request.getIsActive());}
			if (request.getIsVisible()!=null) {user.setIsVisible(request.getIsVisible());}
			if (request.getLoginId()!=null) {user.setLoginId(request.getLoginId());}
			if (request.getNotificationPref()!=null) {user.setNotificationPref(request.getNotificationPref());}
			if (request.getAllowManualSubmission()!=null) {user.setAllowManualSubmission(request.getAllowManualSubmission());}
			if (request.getAllowDashboardView()!=null) {user.setAllowDashboardView(request.getAllowDashboardView());}
			if (request.getThemePref()!=null) {user.setThemePref(request.getThemePref());}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}

}
