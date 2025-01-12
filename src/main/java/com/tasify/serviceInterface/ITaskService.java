package com.tasify.serviceInterface;

import java.util.List;

import com.tasify.entity.Task;
import com.tasify.entity.TaskComment;
import com.tasify.entity.TaskHistory;
import com.tasify.entity.User;

public interface ITaskService 
{
	// Basic CRUD operations
	Task createTask(Task task);
	Task updateTask(Long taskId, Task updatedTask);
	String deleteTask(Long taskId);
	List<Task> getTasksByUserId(Long userid);
	Task getTaskById(Long taskId);
	List<Task> getAllTasks();
	

	List<Task> getAllUsersTasksServiceImpl(Long userId);

	// Status updates
	Task updateTaskStatus(Long taskId, Task.Status status);
	
	

}
