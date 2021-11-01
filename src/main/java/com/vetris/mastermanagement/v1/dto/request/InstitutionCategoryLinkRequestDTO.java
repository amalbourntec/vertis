package com.vetris.mastermanagement.v1.dto.request;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

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

	@NotNull
	private String institutionId;

}
