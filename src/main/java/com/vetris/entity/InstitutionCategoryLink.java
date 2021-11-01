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
 * Entity for InstitutionCategoryLink
 * 
 * @author Jini
 *
 */

@Getter
@Setter
@Entity
@Table(name = "InstitutionCategoryLink")
public class InstitutionCategoryLink extends AuditEntityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id", nullable = false)
	private Integer categoryId;

	@Column(name = "institution_id", nullable = false)
	private String institutionId;

}
