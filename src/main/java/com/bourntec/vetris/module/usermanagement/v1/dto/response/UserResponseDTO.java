package com.bourntec.vetris.module.usermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Amal
 * Response DTO for User
 */
@Getter
@Setter

public class UserResponseDTO {
  
	private String code;
	private String name;
	private String password;
	private String email_id;
	private String contact_no;
	private Integer user_role_id;
	private String first_login;
	private String pacs_user_id;
	private String pacs_password;
	private String created_by;
	private Date date_created;
	private String update_by;
	private Date date_updated;
	private String is_active;
	private String is_visible;
	private String login_id;
	private String notification_pref;
	private String allow_manual_submission;
	private String allow_dashboard_view;
	private String theme_pref;
	private String responseMessage;
	
}
