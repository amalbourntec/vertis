package com.vetris.adminmanagement.v1.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponseDTO {

	private String status;
	private Object payload;
	private String message;
}
