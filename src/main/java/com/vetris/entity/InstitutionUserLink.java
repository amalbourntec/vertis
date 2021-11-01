package com.vetris.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for InstitutionUserLink
 * 
 * @author Dhanesh
 *
 */
@Entity
@Table(name = "institution_user_link")
@Getter
@Setter
@IdClass(InstitutionUserLinkId.class)
public class InstitutionUserLink extends AuditEntityModel {

	@Id
	@Column(name = "user_id")
	private String userId;

	@Id
	@Column(name = "institution_id")
	private String institutionId;

	@Column(name = "user_login_id", length = 50, nullable = true)
	private String userLoginId;

	@Column(name = "user_pwd", length = 200, nullable = true)
	private String userPwd;

	@Column(name = "user_pacs_user_id", length = 20, nullable = true)
	private String userPacsUserId;

	@Column(name = "user_pacs_password", length = 200, nullable = true)
	private String userPacsPassword;

	@Column(name = "user_email", length = 50, nullable = true)
	private String userEmail;

	@Column(name = "granted_rights_pacs", length = 30, nullable = true)
	private String grantedRightsPacs;

	@Column(name = "updated_in_pacs", length = 1, nullable = true)
	private Character updatedInPacs;

	@Column(name = "date_updated_in_pacs", nullable = true)
	private Date dateUpdatedInPacs;

	@Column(name = "user_contact_no", length = 20, nullable = true)
	private String userContactNo;
}
