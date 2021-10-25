package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for InstitutionSalespersonLink
 * 
 * @author Aldrin
 *
 */
@Getter
@Setter
@Entity
@Table(name = "institution_salesperson_link")
public class InstitutionSalespersonLink extends AuditEntityModel {

	@Id
	@Column(name = "salesperson_id", length = 200, nullable = false)
	private String salespersonId;

	@Column(name = "institution_id", length = 200, nullable = false)
	private String institutionId;

	@Column(name = "salesperson_name", length = 200, nullable = true)
	private String salespersonName;

	@Column(name = "salesperson_fname", length = 80, nullable = true)
	private String salespersonFName;

	@Column(name = "salesperson_lname", length = 80, nullable = true)
	private String salespersonLName;

	@Column(name = "salesperson_credentials", length = 30, nullable = true)
	private String salespersonCredentials;

	@Column(name = "salesperson_login_email", length = 50, nullable = true)
	private String salespersonLoginEmail;

	@Column(name = "salesperson_email", length = 50, nullable = true)
	private String salespersonEmail;

	@Column(name = "salesperson_mobile", length = 50, nullable = true)
	private String salespersonMobile;

	@Column(name = "salesperson_user_id", length = 200, nullable = true)
	private String salespersonUserId;

	@Column(name = "salesperson_pacs_user_id", length = 20, nullable = true)
	private String salespersonPacsUserId;

	@Column(name = "salesperson_pacs_password", length = 200, nullable = true)
	private String salespersonPacsPassword;

	@Column(name = "commission_1st_yr", length = 30, nullable = true)
	private Double commission1stYr;

	@Column(name = "commission_2nd_yr", length = 30, nullable = true)
	private Double commission2ndYr;

	@Column(name = "billing_account_id", length = 200, nullable = true)
	private String billingAccountId;
}
