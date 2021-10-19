package com.vetris.enums;

/**
 *  Enum class for creating custom error codes.
 * @author AMAL
 *
 */
public enum ErrorCodes {
	DATA_NOT_FOUND(" not found"),
	UN_AUTH_USER("UnAuthorized User"),
	ACCESS_DENIED("Access Denied");
	
	private String message;

	private ErrorCodes(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}




}
