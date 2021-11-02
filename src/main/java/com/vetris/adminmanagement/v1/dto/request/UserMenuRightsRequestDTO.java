package com.vetris.adminmanagement.v1.dto.request;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * @author anandu
 *
 */
@Setter
@Getter
@Validated
public class UserMenuRightsRequestDTO {

	@NotNull
	private String userId;
	
	@NotNull
	private Integer menuId;
	
}
