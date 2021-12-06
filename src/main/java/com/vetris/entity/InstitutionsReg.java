package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for InstitutionsReg
 * 
 * @author Dhanesh
 *
 */

@Getter
@Setter
@Entity
@Table(name = "institution_reg")
public class InstitutionsReg extends AuditEntityModel {

	@Id
	@Column(name = "id", length = 200)
	private String id;

	@Column(name = "code", length = 5, nullable = true)
	private String code;

	@Column(name = "name", length = 100, nullable = true)
	private String name;

	@Column(name = "address_1", length = 100, nullable = true)
	private String address1;

	@Column(name = "address_2", length = 100, nullable = true)
	private String address2;

	@Column(name = "city", length = 100, nullable = true)
	private String city;

	@Column(name = "state_id", length = 100, nullable = true)
	private Integer stateId;

	@Column(name = "country_id", length = 100, nullable = true)
	private Integer countryId;

	@Column(name = "zip", length = 20, nullable = true)
	private String zip;

	@Column(name = "email_id", length = 50, nullable = true)
	private String emailId;

	@Column(name = "phone_no", length = 30, nullable = true)
	private String phoneNo;

	@Column(name = "mobile_no", length = 20, nullable = true)
	private String mobileNo;

	@Column(name = "contact_person_name", length = 100, nullable = true)
	private String contactPersonName;

	@Column(name = "contact_person_mobile", length = 20, nullable = true)
	private String contactPersonMobile;

	@Column(name = "login_id", length = 20, nullable = true)
	private String loginId;

	@Column(name = "login_password", length = 200, nullable = true)
	private String loginPassword;

	@Column(name = "login_email_id", length = 100, nullable = true)
	private String loginEmailId;

	@Column(name = "login_mobile_no", length = 20, nullable = true)
	private String loginMobileNumber;

	@Column(name = "is_email_verified", length = 1, nullable = true)
	private String isEmailVerified;

	@Column(name = "is_mobile_verified", length = 1, nullable = true)
	private String isMobileVerified;

	@Column(name = "preferred_pmt_method", length = 5, nullable = true)
	private String preferredPmtMethod;
}
