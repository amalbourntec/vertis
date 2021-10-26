package com.vetris.mastermanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

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
	@NotNull
	private String institutionId;

	@Size(max = 50, message = "fee amount  must be atmost 50 double value")
	private Double feeAmount;

	@Size(max = 50, message = "discount per  must be atmost 50 double value")
	private Double discountPer;
}
