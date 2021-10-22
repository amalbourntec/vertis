package com.vetris.usermanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * InstitutionAltNameLink Request DTO class
 * 
 * @author Aldrin
 */
@Getter
@Setter
@Validated
public class InstitutionAltNameLinkRequestDTO {

	@Size(max = 200, message = "institution id  must be atmost 200 characters")
	@NotNull
	private String institutionId;

	@Size(max = 200, message = "alternate Name  must be atmost 200 characters")
	private String alternateName;

}
