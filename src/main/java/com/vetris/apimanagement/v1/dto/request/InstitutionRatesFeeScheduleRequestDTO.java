package com.vetris.apimanagement.v1.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * InstitutionRatesFeeSchedule Request DTO class
 * 
 * @author Aldrin
 */

@Getter
@Setter
@Validated
public class InstitutionRatesFeeScheduleRequestDTO {

	@Size(max = 200, message = "institution id  must be atmost 200 characters")
	@NotBlank(message="Institution Id must not be empty")
	private String institutionId;

	private Double feeAmount;

	private Double discountPer;
}
