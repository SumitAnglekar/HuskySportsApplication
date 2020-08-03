package com.neu.exception;

public class ApplyException extends Exception {

	public ApplyException(String message)
	{
		super("ApplyException-"+message);
	}
	
	public ApplyException(String message, Throwable cause)
	{
		super("ApplyException-"+message,cause);
	}
	
}
