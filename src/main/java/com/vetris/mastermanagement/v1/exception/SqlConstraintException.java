package com.vetris.mastermanagement.v1.exception;

public class SqlConstraintException extends Exception {
	private static final long serialVersionUID = -9079454849611061074L;

	public SqlConstraintException() {
		super();
	}

	public SqlConstraintException(final String message) {
		super(message);
	}

}
