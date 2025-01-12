package com.tasify.Exceptions;

public class TagNotFoundException extends RuntimeException 
{
	public TagNotFoundException(String msg)
	{
		super(msg);
	}
}
