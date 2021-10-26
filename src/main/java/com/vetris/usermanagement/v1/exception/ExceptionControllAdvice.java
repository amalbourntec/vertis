package com.vetris.usermanagement.v1.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for handling the runtime exception
 * 
 * @author Amal
 *
 */
@ControllerAdvice
public class ExceptionControllAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> handleRunTimeException(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(); //
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setTimestamp(new Date());
		response.setStatus(response.getHttpStatus().value());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handleAccessDeniedException(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(); //
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setHttpStatus(HttpStatus.FORBIDDEN);
		response.setTimestamp(new Date());
		response.setStatus(response.getHttpStatus().value());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(Exception ex, final HttpServletRequest request) {

		ExceptionResponse response = new ExceptionResponse(); //
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setHttpStatus(HttpStatus.NOT_FOUND);
		response.setTimestamp(new Date());
		response.setStatus(response.getHttpStatus().value());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	// to handle unauthorized exception returns exception response
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<?> unAuthorizedException(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setHttpStatus(HttpStatus.UNAUTHORIZED);
		response.setTimestamp(new Date());
		response.setStatus(response.getHttpStatus().value());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
	// to handle constraint violation exception returns exception response

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> constraintVoilationexception(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setTimestamp(new Date());
		response.setStatus(response.getHttpStatus().value());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}	
    // to handle Data integrity violation exception returns exception response
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> dataIntegrityViolationException(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setTimestamp(new Date());
		response.setStatus(response.getHttpStatus().value());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
}
