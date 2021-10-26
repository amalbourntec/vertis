package com.vetris.mastermanagement.v1.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 *  class for customizing the exception response
 * @author Jose Eldhose
 *
 */
@Getter
@Setter
public class ExceptionResponse {

	
	private Date timestamp;
	private int status;
	private HttpStatus httpStatus;
	private String responseMessage;
	private String requestURL;
}
