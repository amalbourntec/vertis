package com.vetris.adminmanagement.v1.dto.request;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;
import com.vetris.entity.UserRoles;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jose Eldhose
 *User Role Request DTO class
 */
@Getter
@Setter
@Validated
public class UserRolesRequestDTO {
	

	
	@Size( max = 10, message = "code  must be atmost 10 characters")
	@NotNull
	private String code;
	
	@Size( max = 30, message = "name  must be atmost 30 characters")
	@NotNull
	private String name;
	
	@Size( max = 1, message = "is visible  must be atmost 1 characters")
	private String isVisible;
	
	@Size( max = 1, message = "is active  must be atmost 1 characters")
	@NotNull
	private String isActive;
	
	@Size( max = 200, message = "created by  must be atmost 200 characters")
	@NotNull
	private String createdBy;
	
	@NotNull
	private Date dateCreated;
	
	private String updateBy;
	
	private Date dateUpdated;
	
	@Size( max = 1, message = "sysDefined  must be atmost 1 characters")
	private String sysDefined;
	
	
	/**
	 * @param request
	 * @return user role
	 */
	public UserRoles toUserRolesRequestModel(UserRolesRequestDTO request) {
		UserRoles userRoles=new UserRoles();
		try {
			
			if(request.code!=null) {userRoles.setCode(request.getCode());}
			if(request.name!=null) {userRoles.setName(request.getName());}
			if(request.createdBy!=null) {userRoles.setCreatedBy(request.getCreatedBy());}
			if(request.dateCreated!=null) {userRoles.setDateCreated(request.getDateCreated());}
			if(request.updateBy!=null) {userRoles.setUpdateBy(request.getUpdateBy());}
			if(request.dateUpdated!=null) {userRoles.setDateUpdated(request.getDateUpdated());}
			if(request.isVisible!=null) {userRoles.setIsVisible(request.getIsVisible());}
			if(request.isActive!=null) {userRoles.setIsActive(request.getIsActive());}
			if(request.sysDefined!=null) {userRoles.setSysDefined(request.getSysDefined());}
	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return userRoles;
	}

}
