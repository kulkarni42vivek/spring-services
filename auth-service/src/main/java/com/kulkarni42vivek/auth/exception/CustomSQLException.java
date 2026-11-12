package com.kulkarni42vivek.auth.exception;

public class CustomSQLException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1522264486762448666L;
	
	public CustomSQLException(String errorMessage){
		super(errorMessage);
	}
}