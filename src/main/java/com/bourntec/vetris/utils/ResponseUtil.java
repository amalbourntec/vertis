package com.bourntec.vetris.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUtil {
	private String status;
	private Object payload;
	private String message;

}
