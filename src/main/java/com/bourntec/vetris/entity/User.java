package com.bourntec.vetris.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * Entity for UserManagement
 * @author Anandu
 *
 */


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "code",length = 40,nullable=true)
	private String code;
	
	@Column(name = "name",length = 200,nullable=true)
	private String name;
	
	@Column(name = "password",length = 200,nullable=false)
	private String password;

	@Column(name = "email_id",length = 100,nullable=true)
	private String email_id;

	@Column(name = "contact_no",length = 20,nullable=true)
	private String contact_no;
	
	@Column(name = "user_role_id",nullable=false)
	private Integer user_role_id;
	
	@Column(name = "first_login",length = 1,nullable=true)
	private String first_login;
	
	@Column(name = "pacs_user_id",length = 50,nullable=true)
	private String pacs_user_id;

	@Column(name = "pacs_password",length = 200,nullable=false)
	private String pacs_password;
	
	@Column(name = "created_by",length = 200,nullable=false,unique = true)
	private String created_by;

	@Column(name = "date_created",nullable=false)
	private Date date_created;
	
	@Column(name = "update_by",nullable=true, unique = true)
	private String update_by;
	
	@Column(name = "date_updated",nullable=true)
	private Date date_updated;

	@Column(name = "is_active",length = 1,nullable=true)
	private String is_active;
	
	@Column(name = "is_visible",length = 1,nullable=true)
	private String is_visible;

	@Column(name = "login_id",length = 50,nullable=true)
	private String login_id;
	
	@Column(name = "notification_pref",length = 1,nullable=true)
	private String notification_pref;

	@Column(name = "allow_manual_submission",length = 1,nullable=true)
	private String allow_manual_submission;
	
	@Column(name = "allow_dashboard_view",length = 1,nullable=true)
	private String allow_dashboard_view;
	
	@Column(name = "theme_pref",length = 10,nullable=true)
	private String theme_pref;

}
