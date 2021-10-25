package com.vetris.entity;

import java.io.Serializable;


public class InstitutionDisputeDicomTagsId implements Serializable {

	
	private String institutionId;
	
	private String groupId;
	
	private String elementId;
	
	public InstitutionDisputeDicomTagsId() {
		
	}
	public InstitutionDisputeDicomTagsId(String institutionId,String groupId,String elementId) {
		this.institutionId=institutionId;
		this.groupId=groupId;
		this.elementId=elementId;
	}
}
