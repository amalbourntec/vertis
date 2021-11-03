package com.vetris.apimanagement.v1.dto.request;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dhanesh InstitutionRegDTO class
 */

@Getter
@Setter
@Validated
public class InstitutionsRegRequestDTO {

	private String id;
	
	@Size(max = 5, message = "code  must be atmost 5 characters")
	private String code;

	@Size(max = 100, message = "name  must be atmost 100 characters")
	private String name;

	@Size(max = 100, message = "address1  must be atmost 100 characters")
	private String address1;

	@Size(max = 100, message = "address2  must be atmost 100 characters")
	private String address2;

	@Size(max = 100, message = "city  must be atmost 100 characters")
	private String city;

	@Size(max = 10, message = "stateId  must be atmost 10 characters")
	private Integer stateId;

	@Size(max = 10, message = "countryId  must be atmost 10 characters")
	private Integer countryId;

	@Size(max = 20, message = "zip  must be atmost 20 characters")
	private String zip;

	@Size(max = 50, message = "emailId  must be atmost 50 characters")
	private String emailId;

	@Size(max = 30, message = "phoneNo  must be atmost 30 characters")
	private String phoneNo;

	@Size(max = 20, message = "mobileNo  must be atmost 20 characters")
	private String mobileNo;

	@Size(max = 100, message = "contactPersonName  must be atmost 100 characters")
	private String contactPersonName;

	@Size(max = 20, message = "contactPersonMobile  must be atmost 20 characters")
	private String contactPersonMobile;

	@Size(max = 20, message = "loginId  must be atmost 20 characters")
	private String loginId;

	@Size(max = 200, message = "loginPassword  must be atmost 200 characters")
	private String loginPassword;

	@Size(max = 100, message = "loginEmailId  must be atmost 100 characters")
	private String loginEmailId;

	@Size(max = 20, message = "loginMobileNumber  must be atmost 20 characters")
	private String loginMobileNumber;

	@Size(max = 1, message = "isEmailVerified  must be atmost 1 characters")
	private Character isEmailVerified;

	@Size(max = 1, message = "isMobileVerified  must be atmost 1 characters")
	private Character isMobileVerified;

	@Size(max = 5, message = "preferredPmtMethod  must be atmost 5 characters")
	private String preferredPmtMethod;

	@Size(max = 200, message = "created by  must be atmost 200 characters")
	@NotNull
	private String createdBy;

	@NotNull
	private Date dateCreated;

	private String updateBy;

	private Date dateUpdated;
}
