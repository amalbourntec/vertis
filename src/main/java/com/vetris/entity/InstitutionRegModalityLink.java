package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for Institution registration modality.
 * 
 * @author Jose Eldhose
 *
 */
@Entity
@Getter
@Setter
@Table(name = "institution_reg_modality_link")
public class InstitutionRegModalityLink extends AuditEntityModel {

	@Id
	@Column(name = "modality_id", nullable = false, length = 100, unique = true)
	private String modalityId;

	@Column(name = "institution_id", nullable = false, length = 100, unique=true )
	private String institutionId;

}
