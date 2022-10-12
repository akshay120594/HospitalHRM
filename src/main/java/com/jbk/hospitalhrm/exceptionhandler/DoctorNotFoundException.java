package com.jbk.hospitalhrm.exceptionhandler;

public class DoctorNotFoundException extends RuntimeException {
	public DoctorNotFoundException(String msg) {
		super(msg);
	}

}
