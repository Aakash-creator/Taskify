package com.tasify.Exceptions;

public class TaskCommentNotFoundException extends RuntimeException 
{
	public TaskCommentNotFoundException(String msg)
	{
		super(msg);
	}
}
