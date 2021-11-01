
package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for InstitutionDisputeDicomTags
 * 
 * @author Dhanesh
 *
 */

@Getter
@Setter
@Entity
@Table(name = "institution_dispute__dicom_tags")
@IdClass(InstitutionDisputeDicomTagsId.class)
public class InstitutionDisputeDicomTags extends AuditEntityModel {

	@Id
	private String institutionId;

	@Id
	private String groupId;

	@Id
	private String elementId;

	@Column(name = "default_value", length = 250, nullable = true)
	private String defaultValue;

	@Column(name = "junk_characters", length = 100, nullable = true)
	private String junkCharacters;

	public InstitutionDisputeDicomTags() {
		super();
	}

}
