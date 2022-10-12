package com.jbk.hospitalhrm.exceptionhandler;

public class PatientNotFoundException extends RuntimeException{
	public PatientNotFoundException(String msg) {
		super(msg);
	}

}
