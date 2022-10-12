package com.jbk.hospitalhrm.exceptionhandler;

public class NotEnoughContent extends RuntimeException {
	public NotEnoughContent(String msg) {
		super(msg);
	}
}
