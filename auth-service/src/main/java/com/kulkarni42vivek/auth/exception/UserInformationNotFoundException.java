package com.kulkarni42vivek.auth.exception;

public class UserInformationNotFoundException extends RuntimeException {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7273391258222247366L;

	public UserInformationNotFoundException(String errorMessage){
		super(errorMessage);
	}
}