package com.vetris.usermanagement.v1.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setTimestamp(new Date());
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {

		ExceptionResponse response = new ExceptionResponse();
		response.setRequestURL(request.getRequestURI());
		response.setResponseMessage(exception.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setTimestamp(new Date());
		response.setHttpStatus(HttpStatus.NOT_FOUND);
		return response;
	}

	// to handle unauthorized exception returns exception response
	@ExceptionHandler(UnAuthorizedException.class)
	public ResponseEntity<?> unAuthorizedException(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setTimestamp(new Date());
		response.setHttpStatus(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
	// to handle constraint violation exception returns exception response

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> constraintVoilationexception(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setTimestamp(new Date());
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
	// to handle vetris business exception returns exception response

	@ExceptionHandler(VetrisBusinessException.class)
	public ResponseEntity<?> vetrisBusinessException(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setTimestamp(new Date());
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> dataIntegrityViolationException(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setTimestamp(new Date());
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
}
