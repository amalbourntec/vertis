package com.vetris.usermanagement.v1.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/**
 *  Exception handler for handling the runtime exception
 * @author Amal
 *
 */
@ControllerAdvice
public class ExceptionControllAdvice extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> handleRunTimeException(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(response,response.getHttpStatus());
	}
}
