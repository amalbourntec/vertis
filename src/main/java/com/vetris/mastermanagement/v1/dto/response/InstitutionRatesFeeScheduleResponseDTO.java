package com.vetris.mastermanagement.v1.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO for InstitutionRatesFeeSchedule
 * 
 * @author Aldrin
 */
@Getter
@Setter
public class InstitutionRatesFeeScheduleResponseDTO {

	private String institutionId;

	private Double feeAmount;

	private Double discountPer;

	private String createdBy;

	private Date dateCreated;

	private String updateBy;

	private Date dateUpdated;
}
