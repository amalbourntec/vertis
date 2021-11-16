package com.vetris.apimanagement.v1.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Dhanesh InstitutionDisputeDicomTagsRequestDTO class
 */

@Getter
@Setter
@Validated
public class InstitutionDisputeDicomTagsRequestDTO {

	@Size(max = 5, message = "institutionId  must be atmost 5 characters")
	@NotBlank(message = "institutionId must not be empty")
	private String institutionId;

	@Size(max = 5, message = "groupId must be atmost 5 characters")
	@NotBlank(message = "groupId must not be empty")
	private String groupId;

	@Size(max = 5, message = "elementId must be atmost 5 characters")
	@NotBlank(message = "elementId must not be empty")
	private String elementId;

	@Size(max = 250, message = "defaultValue must be atmost 250 characters")
	private String defaultValue;

	@Size(max = 250, message = "junkCharacters must be atmost 250 characters")
	private String junkCharacters;

}
