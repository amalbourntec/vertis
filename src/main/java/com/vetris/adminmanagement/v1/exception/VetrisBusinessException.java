package com.vetris.adminmanagement.v1.exception;

public class VetrisBusinessException extends Exception{

	
	/**
	 * @author ShekarReddySamreddy
	 */
	private static final long serialVersionUID = -2214972594140363389L;

	public VetrisBusinessException(String message, Throwable cause) {
		super(message,cause);
		
	}
	public VetrisBusinessException(String message) {
		super(message);
		
	}
	public VetrisBusinessException( Throwable cause) {
		super(cause);
		 
	}
}
