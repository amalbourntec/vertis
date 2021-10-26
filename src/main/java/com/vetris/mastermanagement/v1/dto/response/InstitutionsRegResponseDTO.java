package com.vetris.mastermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dhanesh InstitutionDisputeDicomTagsResponseDTO class
 */
@Getter
@Setter

public class InstitutionsRegResponseDTO {

	private String id;

	private String code;

	private String name;

	private String address1;

	private String address2;

	private String city;

	private Integer stateId;

	private Integer countryId;

	private String zip;

	private String emailId;

	private String phoneNo;

	private String mobileNo;

	private String contactPersonName;

	private String contactPersonMobile;

	private String loginId;

	private String loginPassword;

	private String loginEmailId;

	private String loginMobileNumber;

	private Character isEmailVerified;

	private Character isMobileVerified;

	private String preferredPmtMethod;

	private String updateBy;

	private Date dateUpdated;
}
