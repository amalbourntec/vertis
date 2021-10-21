package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for InstitutionAltNameLink
 * @author Aldrin
 *
 */
@Getter
@Setter
@Entity
@Table(name = "institution_alt_name_link")
public class InstitutionAltNameLink extends AuditEntityModel{
	
	@Id
	@Column(name = "id", length = 10)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(name = "institution_id", length = 10)
	private Integer institutionId;
	
	@Column(name = "alternate_name", length = 200, nullable = true)
	private String alternateName;
	
	
}
