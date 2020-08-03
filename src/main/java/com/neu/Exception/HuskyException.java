package com.neu.Exception;

public class HuskyException extends Exception {

	public HuskyException(String message)
	{
		super("HuskyException-"+message);
	}
	
	public HuskyException(String message, Throwable cause)
	{
		super("HuskyException-"+message,cause);
	}
	
}
