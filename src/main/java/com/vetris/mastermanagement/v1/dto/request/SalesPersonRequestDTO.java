package com.vetris.mastermanagement.v1.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * requestDTO for salesPerson
 * 
 * @author ShekarReddySamreddy
 * 
 */
@Getter
@Setter
@Validated
@ToString
public class SalesPersonRequestDTO {

	@Size(max = 10, message = "code  must be atmost 10 characters")
	private String code;

	@Size(max = 80, message = "fname  must be atmost 80 characters")
	private String fName;

	@Size(max = 80, message = "iname  must be atmost 80 characters")
	private String iName;

	@Size(max = 200, message = "name  must be atmost 200 characters")
	@NotNull
	private String name;

	@Size(max = 100, message = "address_1 must be atmost 100 characters")
	private String address1;

	@Size(max = 100, message = "address_2 must be atmost 100 characters")
	private String address2;

	@Size(max = 100, message = "city must be atmost 100 characters")
	private String city;

	private Integer stateId;

	private Integer countryId;

	@Size(max = 20, message = "zip must be atmost 20 characters")
	private String zip;

	@Size(max = 50, message = "email_id must be atmost 50 characters")
	@NotNull
	private String emailId;

	@Size(max = 30, message = "phone_no must be atmost 30 characters")
	private String phoneNo;

	@Size(max = 20, message = "mobile_no must be atmost 20 characters")
	private String mobileNo;

	@Size(max = 10, message = "pacs_user_id must be atmost 10 characters")
	private String pacsUserId;

	@Size(max = 200, message = "pacs_password must be atmost 200 characters")
	private String pacsPassword;

	@Size(max = 1, message = "is_active must be atmost 1 characters")
	private String isActive;

	private String loginUserId;

	@Size(max = 50, message = "login_id must be atmost 50 characters")
	private String loginId;

	@Size(max = 200, message = "login_pwd must be atmost 200 characters")
	private String loginPwd;

	@Size(max = 1, message = "notification_pref must be atmost 1 characters")
	private String notificationPref;

}
