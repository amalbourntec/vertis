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
public class UserRoleMenuRightsRequestDTO {


	@NotNull
	private Integer userRoleId;
	
	@NotNull
	private Integer menuId;
	
}
