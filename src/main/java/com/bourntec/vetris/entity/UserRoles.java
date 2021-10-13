package com.bourntec.vetris.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for UserRoles
 * @author Jose
 *
 */

@Getter
@Setter
@Entity
@Table(name="userroles")
@SQLDelete(sql = "UPDATE userroles SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class UserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", length=200)
	private Integer id;
	
	@Column(name="code", length=10,nullable = false)
	private String code;
	
	@Column(name="name", length=30, nullable=false)
	private String name;
	
	@Column(name="is_visible", length=1,nullable = true)
	private String isVisible;
	
	@Column(name="is_active", length=1,nullable = true)
	private String isActive;
	
	@Column(name="createdby", length=200,nullable = false)
	private String createdBy;
	
	@Column(name="date_created",nullable = false)
	private Date dateCreated;
	
	@Column(name="updateby", length=200,nullable = true)
	private String updateBy;
	
	@Column(name="date_updated",nullable = true)
	private Date dateUpdated;
	
	@Column(name="sys_defined", length=1,nullable = true)
	private String sysDefined;
	
	@Column(name="deleted")
	private boolean deleted = Boolean.FALSE;
	

}
