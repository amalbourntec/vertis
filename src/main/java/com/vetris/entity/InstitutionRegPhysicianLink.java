package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for Physician
 * 
 * @author Jose Eldhose
 *
 */
@Entity
@Getter
@Setter
@Table(name = "institution_reg_physician_link")
public class InstitutionRegPhysicianLink extends AuditEntityModel {

	@Id
	@Column(name = "physician_id", nullable = false, unique = true)
	private String physicianId;

	@Column(name = "institution_id", nullable = false, unique = true)
	private String institutionId;

	@Column(name = "physician_name", nullable = true, length = 200)
	private String physicianName;

	@Column(name = "physician_fname", nullable = true, length = 80)
	private String physicianFname;

	@Column(name = "physician_lname", nullable = true, length = 80)
	private String physicianLname;

	@Column(name = "physician_credentials", nullable = true, length = 30)
	private String physicianCredentials;

	@Column(name = "physician_email", nullable = true, length = 500)
	private String physicianEmail;

	@Column(name = "physician_mobile", nullable = true, length = 500)
	private String physicianMobile;

}
