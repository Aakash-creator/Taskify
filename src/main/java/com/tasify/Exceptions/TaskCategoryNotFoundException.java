package com.tasify.Exceptions;

public class TaskCategoryNotFoundException extends RuntimeException
{
	public TaskCategoryNotFoundException(String msg)
	{
		super(msg);
	}
}
