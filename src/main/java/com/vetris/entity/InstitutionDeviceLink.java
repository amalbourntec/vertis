package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for InstitutionDeviceLink
 * @author Aldrin
 *
 */
@Getter
@Setter
@Entity
@Table(name = "institution_device_link")
public class InstitutionDeviceLink extends AuditEntityModel {

	@Id
	@Column(name = "device_id", length = 10)
	private Integer deviceId;

	@Column(name = "institution_id", length = 10, nullable = false)
	private Integer institutionId;

	@Column(name = "manufacturer", length = 200, nullable = false)
	private String manufacturer;

	@Column(name = "model", length = 200, nullable = true)
	private String model;

	@Column(name = "serial_no", length = 20, nullable = true)
	private String serialNo;

	@Column(name = "modality_ae_title", length = 50, nullable = true)
	private String modalityAeTitle;

	@Column(name = "modality", length = 30, nullable = true)
	private String modality;

	@Column(name = "weight_uom", length = 10, nullable = true)
	private String weightUom;

}
