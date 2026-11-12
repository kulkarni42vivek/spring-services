package com.example.demo.Exception;

public class PatientNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2203249493178171209L;
	
	public PatientNotFound(String msg){
		super(msg);
	}

}
