package com.vetris.adminmanagement.v1.exception;

public class UnAuthorizedException extends Exception{

	/**
	 * @author ShekarReddySamreddy
	 */
	private static final long serialVersionUID = -3695088041826677118L;
	
	
	public UnAuthorizedException(String message,Throwable cause) {
		super(message,cause);
		
	}
	
	public UnAuthorizedException(Throwable cause) {
		super(cause);		
		
	}
	
	public UnAuthorizedException(String message) {
		super(message);	
		
	}
}
