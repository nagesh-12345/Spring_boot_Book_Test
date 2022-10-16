package com.demo.in.exceptions;

public class NoDataFoundException  extends RuntimeException{

	public NoDataFoundException() {
		// TODO Auto-generated constructor stub
	}
	public NoDataFoundException(String msg) {
		super(msg);
		System.out.println(msg);
	}
	
}
