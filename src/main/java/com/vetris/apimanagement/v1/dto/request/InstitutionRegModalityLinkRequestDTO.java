package com.vetris.apimanagement.v1.dto.request;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO for Institution registration modality.
 * 
 * @author Jose Eldhose
 *
 */
@Getter
@Setter
@Validated
public class InstitutionRegModalityLinkRequestDTO {

	@NotBlank(message = "Instirution Id must not be empty")
	private String institutionId;

}
