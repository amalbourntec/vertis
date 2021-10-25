package com.vetris.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity for InstitutionRatesFeeSchedule
 * 
 * @author Aldrin
 *
 */
@Getter
@Setter
@Entity
@Table(name = "institution_rates_fee_schedule")
public class InstitutionRatesFeeSchedule extends AuditEntityModel {

	@Id
	@Column(name = "rate_id", length = 200)
	private String rateId;

	@Column(name = "institution_id", length = 200, nullable = false)
	private String institutionId;

	@Column(name = "fee_amount", length = 50, nullable = true)
	private Double feeAmount;

	@Column(name = "discount_per", length = 50, nullable = true)
	private Double discountPer;

}
