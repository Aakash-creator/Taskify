package com.tasify.Exceptions;

import com.tasify.entity.Task;

public class InputMismatchException extends RuntimeException
{
	public InputMismatchException(String msg)
	{
		super(msg);
	}
}
