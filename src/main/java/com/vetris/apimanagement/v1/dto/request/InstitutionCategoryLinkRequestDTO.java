package com.vetris.apimanagement.v1.dto.request;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * Institution Category Link Request DTO class
 * 
 * @author Jini
 *
 */

@Getter
@Setter
@Validated
public class InstitutionCategoryLinkRequestDTO {

	@NotBlank(message = "InstitutionId must not be empty")
	private String institutionId;

}
