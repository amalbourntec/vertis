package com.vetris.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Donepudi Suresh
 *
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AuditEntityModel {
	
	@Column(name="created_by", length=200,nullable = false)
	private String createdBy;
	
	@Column(name="date_created",nullable = false)
	private Date dateCreated;
	
	@Column(name="update_by", length=200,nullable = true)
	private String updateBy;
	
	@Column(name="date_updated",nullable = true)
	private Date dateUpdated;

}
