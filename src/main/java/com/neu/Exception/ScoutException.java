package com.neu.exception;

public class ScoutException extends Exception {

	public ScoutException(String message)
	{
		super("ScoutException-"+message);
	}
	
	public ScoutException(String message, Throwable cause)
	{
		super("ScoutException-"+message,cause);
	}
	
}
