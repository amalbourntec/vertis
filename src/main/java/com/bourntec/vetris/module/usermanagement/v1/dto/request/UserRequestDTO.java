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
	private String email_id;

	@Size( max = 20, message = "contact no  must be atmost 20 characters")
	private String contact_no;
	
	@NotNull
	private Integer user_role_id;
	
	private String first_login;
	
	@Size( max = 50, message = "pacs_user_id  must be atmost 50 characters")
	private String pacs_user_id;

	@Size( max = 200, message = "pacs_password  must be atmost 200 characters")
	@NotNull
	private String pacs_password;
	
	@Size( max = 200, message = "created by  must be atmost 200 characters")
	private String created_by;

	@NotNull
	private Date date_created;
	
	private String update_by;

	private Date date_updated;

	@Size( max = 1, message = "is active  must be atmost 1 characters")
	private String is_active;
	
	@Size( max = 1, message = "is visible  must be atmost 1 characters")
	private String is_visible;

	@Size( max = 50, message = "login id  must be atmost 50 characters")
	private String login_id;
	
	@Size( max = 1, message = "notfication_pref  must be atmost 1 characters")
	private String notification_pref;

	@Size( max = 1, message = "allow_manual_submission  must be atmost 1 characters")
	private String allow_manual_submission;
	
	@Size( max = 1, message = "allow_dashboard_view  must be atmost 1 characters")
	private String allow_dashboard_view;
	
	@Size( max = 10, message = "theme_pref  must be atmost 10 characters")
	private String theme_pref;

	
	
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
			if (request.getEmail_id()!=null) {user.setEmail_id(request.getEmail_id());}
			if (request.getContact_no()!=null) {user.setContact_no(request.getContact_no());}
			if (request.getUser_role_id()!=null) {user.setUser_role_id(request.getUser_role_id());}
			if (request.getFirst_login()!=null) {user.setFirst_login(request.getFirst_login());}
			if (request.getPacs_user_id()!=null) {user.setPacs_user_id(request.getPacs_user_id());}
			if (request.getPacs_password()!=null) {user.setPacs_password(request.getPacs_password());}
			if (request.getCreated_by()!=null) {user.setCreated_by(request.getCreated_by());}
			if (request.getDate_created()!=null) {user.setDate_created(request.getDate_created());}
			if (request.getUpdate_by()!=null) {user.setUpdate_by(request.getUpdate_by());}
			if (request.getDate_created()!=null) {user.setDate_updated(request.getDate_created());}
			if (request.getIs_active()!=null) {user.setIs_active(request.getIs_active());}
			if (request.getIs_visible()!=null) {user.setIs_visible(request.getIs_visible());}
			if (request.getLogin_id()!=null) {user.setLogin_id(request.getLogin_id());}
			if (request.getNotification_pref()!=null) {user.setNotification_pref(request.getNotification_pref());}
			if (request.getAllow_manual_submission()!=null) {user.setAllow_manual_submission(request.getAllow_manual_submission());}
			if (request.getAllow_dashboard_view()!=null) {user.setAllow_dashboard_view(request.getAllow_dashboard_view());}
			if (request.getTheme_pref()!=null) {user.setTheme_pref(request.getTheme_pref());}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	}

}
