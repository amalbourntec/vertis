package com.vetris.entity;

import java.io.Serializable;

public class InstitutionUserLinkId implements Serializable {

	private String userId;
	private String institutionId;

	public InstitutionUserLinkId() {

	}

	public InstitutionUserLinkId(String userId, String institutionId) {

		this.userId = userId;
		this.institutionId = institutionId;
	}

}
