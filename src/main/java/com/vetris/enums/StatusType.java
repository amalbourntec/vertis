package com.vetris.enums;

public enum StatusType {
	SUCCESS("Success"),
	FAILURE("Failure");
	
	private String message;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param message
	 */
	private StatusType(String message) {
		this.message = message;
	}
	
	
	
}
