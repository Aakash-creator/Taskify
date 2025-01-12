package com.tasify.Exceptions;

public class TaskNotFoundException extends RuntimeException
{
	public TaskNotFoundException(String msg)
	{
		super(msg);
	}
}
