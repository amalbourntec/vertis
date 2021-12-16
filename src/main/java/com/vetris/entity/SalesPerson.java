package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * entity for salesPerson
 * 
 * @author ShekarReddySamreddy
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "SalesPerson")
@AllArgsConstructor
@NoArgsConstructor
public class SalesPerson extends AuditEntityModel {

	@Id
	@Column(name = "id", length = 200)
	private String id;

	@Column(name = "code", length = 10, nullable = true)
	private String code;

	@Column(name = "fname", length = 80, nullable = true)
	private String fName;

	@Column(name = "Iname", length = 80, nullable = true)
	private String iName;

	@Column(name = "name", length = 200, nullable = false)
	@NotNull
	private String name;

	@Column(name = "address_1", length = 100, nullable = true)
	private String address1;

	@Column(name = "address_2", length = 100, nullable = true)
	private String address2;

	@Column(name = "city", length = 100, nullable = true)
	private String city;

	@Column(name = "state_id", nullable = true)
	private Integer stateId;

	@Column(name = "country_id", nullable = true)
	private Integer countryId;

	@Column(name = "zip", length = 20, nullable = true)
	private String zip;

	@Column(name = "email_id", length = 50, nullable = true)
	private String emailId;

	@Column(name = "phone_no", length = 13, nullable = true)
	private String phoneNo;

	@Column(name = "mobile_no", length = 13, nullable = true)
	private String mobileNo;

	@Column(name = "pacs_user_id", length = 10, nullable = true)
	private String pacsUserId;

	@Column(name = "pacs_password", length = 200, nullable = true)
	private String pacsPassword;

	@Column(name = "is_active", length = 1, nullable = true)
	private String isActive;

	@Column(name = "login_user_id", nullable = true)
	private String loginUserId;

	@Column(name = "login_id", length = 50, nullable = true)
	private String loginId;

	@Column(name = "login_pwd", length = 200, nullable = true)
	private String loginPwd;

	@Column(name = "notification_pref", length = 1, nullable = true)
	private String notificationPref;

}
