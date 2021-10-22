package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for InstitutionPhysicianLink
 * @author Jini
 *
 */

@Getter
@Setter
@Entity
@Table(name = "InstitutionPhysicianLink")
public class InstitutionPhysicianLink extends AuditEntityModel {
	

	@Id
	@Column (name = "physician_id", nullable = false)
	private String physicianId;
	
	@Column (name = "institution_id", nullable = false)
	private String institutionId; 
	
	@Column (name = "physician_name", length = 200, nullable = true)
	private String physicianName;
	
	@Column (name = "physician_fname", length = 80, nullable = true)
	private String physicianFname;
	
	@Column (name = "physician_lname", length = 80, nullable = true)
	private String physicianLname;
	
	@Column (name = "physician_credentials", length = 30, nullable = true)
	private String physicianCredentials;
	
	@Column (name = "physician_login_email", length = 50, nullable = true)
	private String physicianLoginEmail;
	
	@Column (name = "physician_email", length = 500, nullable = true)
	private String physicianEmail;
	
	@Column (name = "physician_mobile", length = 500, nullable = true)
	private String physicianMobile;
	
	@Column (name = "physician_user_id",  nullable = true)
	private String physicianUserId;
	
	@Column (name = "physician_pacs_user_id", length = 10, nullable = true)
	private String physicianPacsUserId;
	
	@Column (name = "physician_pacs_password",length = 200, nullable = true)
	private String physicianPacsPassword;
	
	@Column (name = "billing_account_id", nullable = true)
	private String billingAccountId;


}
