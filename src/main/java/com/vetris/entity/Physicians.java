package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anandu
 *
 */
@Entity
@Getter
@Setter
@Table(name = "physicians")
public class Physicians extends AuditEntityModel{
	
	@Id
	@Column(name = "id" ,nullable = false)
	private String id;
	
	@Column(name = "code" ,nullable = true ,length = 10)
	private String code;
	
	@Column(name = "fname" ,nullable = true,length = 80)
	private String fname;

	@Column(name = "lname" ,nullable = true, length = 80)
	private String lname;

	@Column(name = "credentials" ,nullable = true, length = 30)
	private String credentials;
	
	@Column(name = "name" ,nullable = false, length = 200)
	private String name;

	@Column(name = "address_1" ,nullable = true, length = 100)
	private String address1;

	@Column(name = "address_2" ,nullable = true, length = 100)
	private String address2;

	@Column(name = "city" ,nullable = true, length = 100)
	private String city;
	
	@Column(name = "state_id" ,nullable = true)
	private Integer stateId;

	@Column(name = "country_id" ,nullable = true)
	private Integer countryId;

	@Column(name = "zip" ,nullable = true, length = 20)
	private String zip;

	@Column(name = "email_id" ,nullable = true , length = 500)
	private String emailId;
	
	@Column(name = "phone_no" ,nullable = true , length = 13)
	private String phoneNo;
	
	@Column(name = "mobile_no" ,nullable = true , length = 13)
	private String mobileNo;
	
	@Column(name = "is_active" ,nullable = true , length = 1)
	private String isActive;
	
	@Column(name = "institution_id" ,nullable = true )
	private String institutionId;
	
}

