package com.vetris.usermanagement.v1.dto.request;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

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


	@NotNull
	private String institutionId;

}
