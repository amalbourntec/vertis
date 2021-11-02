package com.vetris.apimanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anandu
 *
 */
@Setter
@Getter
public class PhysicianResponseDTO {

	String id;
	String code;
	String name;
	String isActive;
	String createdBy;
	Date dateCreated;
	String updatedBy;
	Date dateupdated;
	 String fname;
	 String lname;
	 String credentials;
	 String address1;
	 String address2;
	 String city;
	 Integer stateId;
	 Integer countryId;
	 String zip;
	 String emailId;
	 String phoneNo;
	 String mobileNo;
	 String institutionId;
	
}
