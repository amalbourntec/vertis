package com.vetris.entity;

import java.io.Serializable;

/**
 * Class for generating PK values for InstitutionDisputeDicomTags
 * 
 * @author Dhanesh
 *
 */
public class InstitutionDisputeDicomTagsId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String institutionId;

	private String groupId;

	private String elementId;

	public InstitutionDisputeDicomTagsId() {

	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public InstitutionDisputeDicomTagsId(String institutionId, String groupId, String elementId) {
		this.setInstitutionId(institutionId);
		this.setGroupId(groupId);
		this.setElementId(elementId);
		;
	}

}
