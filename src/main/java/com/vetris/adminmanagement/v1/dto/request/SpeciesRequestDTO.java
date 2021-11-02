package com.vetris.adminmanagement.v1.dto.request;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class SpeciesRequestDTO {

	@NotNull
	@Size(max = 10, message = "code  must be atmost 10 characters")
	String code;

	@NotNull
	@Size(max = 50, message = "name  must be atmost 50 characters")
	String name;

	@Size(max = 1, message = "isActive must be atmost 1 characters")
	String isActive;

}
