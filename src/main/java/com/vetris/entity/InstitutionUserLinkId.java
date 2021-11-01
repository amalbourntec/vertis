package com.vetris.entity;

import java.io.Serializable;

/**
 * Class for generating PK values for InstitutionUserLink
 * 
 * @author Dhanesh
 *
 */
public class InstitutionUserLinkId implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String institutionId;

	public InstitutionUserLinkId() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public InstitutionUserLinkId(String userId, String institutionId) {

		this.setUserId(userId);
		this.setInstitutionId(institutionId);
	}

}
