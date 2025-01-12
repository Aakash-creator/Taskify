package com.tasify.Exceptions;

public class TaskAssignmentNotFoundException extends RuntimeException 
{
	public TaskAssignmentNotFoundException(String msg)
	{
		super(msg);
	}
}
