package com.neu.exception;

public class AppPostException extends Exception {

	public AppPostException(String message)
	{
		super("AppPostException-"+message);
	}
	
	public AppPostException(String message, Throwable cause)
	{
		super("AppPostException-"+message,cause);
	}
	
}
