package com.vetris.apimanagement.v1.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for handling the runtime exception
 * 
 * @author Jose Eldhose
 *
 */
@ControllerAdvice
public class ExceptionControllAdvice extends ResponseEntityExceptionHandler {

	/**
	 * @param ex
	 * @param request
	 * @return Exception message
	 */
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> handleRunTimeException(Exception ex, final HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		response.setTimestamp(new Date());
		response.setStatus(response.getHttpStatus().value());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	/**
	 * @param ex
	 * @param request
	 * @return Exception message
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(Exception ex, final HttpServletRequest request) {

		ExceptionResponse response = new ExceptionResponse(); //
		response.setResponseMessage(ex.getMessage());
		response.setRequestURL(request.getRequestURI());
		response.setHttpStatus(HttpStatus.NOT_FOUND);
		response.setTimestamp(new Date());
		response.setStatus(response.getHttpStatus().value());
		System.out.println(response.getHttpStatus());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	/**
	 * @param ex
	 * @param request
	 * @return Exception message
	 */
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

	/**
	 * @param ex
	 * @param request
	 * @return Exception message
	 */
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

	/**
	 * @param ex
	 * @param request
	 * @return Exception message
	 */
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

	/**
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return Exception message
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		List<String> a = new ArrayList<String>();
		for (Object object : (ex.getBindingResult().getAllErrors())) {
			if (object instanceof ObjectError) {
				ObjectError objectError = (ObjectError) object;
				a.add(objectError.getDefaultMessage());
				response.setResponseMessage(a.toString());
			}
		}
		response.setRequestURL(((ServletWebRequest) request).getRequest().getRequestURL().toString());
		response.setHttpStatus(HttpStatus.BAD_REQUEST);
		response.setTimestamp(new Date());
		response.setStatus(response.getHttpStatus().value());
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

}
