package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for InstitutionAltNameLink
 * 
 * @author Aldrin
 *
 */
@Getter
@Setter
@Entity
@Table(name = "institution_alt_name_link")
public class InstitutionAltNameLink extends AuditEntityModel {

	@Id
	@Column(name = "institution_id", length = 200)
	private String institutionId;

	@Column(name = "alternate_name", length = 200, nullable = true)
	private String alternateName;

}
