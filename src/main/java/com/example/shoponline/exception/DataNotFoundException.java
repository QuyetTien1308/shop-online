package com.example.shoponline.exception;

public class DataNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String str) {
		super(str);
	}
}
