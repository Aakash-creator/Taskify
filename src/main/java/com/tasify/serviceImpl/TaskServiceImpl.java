package com.tasify.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasify.Exceptions.InputMismatchException;
import com.tasify.Exceptions.TaskNotFoundException;
import com.tasify.Exceptions.UserNotFoundException;
import com.tasify.Repo.ITaskRepo;
import com.tasify.Repo.IUserRepo;
import com.tasify.entity.Task;
import com.tasify.entity.Task.Status;
import com.tasify.entity.User;
import com.tasify.serviceInterface.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService 
{
	@Autowired
	private ITaskRepo taskRepo;
	
	@Autowired
	private IUserRepo userRepo;
	
	

	@Override
	public Task createTask(Task task) 
	{
		// Check if the user exists, throw an exception if not found
		User userExt = userRepo.findById(task.getUserId()).orElseThrow(()->
		new UserNotFoundException("User not found with id " + task.getUserId()));
		if(userExt!=null)
		{
			// Set the creation and last update timestamps
			task.setCreateDateTime(LocalDateTime.now());
			task.setLastUdateDateTime(LocalDateTime.now());
			
			// Save and return the newly created task
			return taskRepo.save(task);
		}
		return null;
	}

	@Override
	public List<Task> getTasksByUserId(Long userid)
	{
		// Check if the user exists before fetching their tasks
		User user = userRepo.findById(userid).orElseThrow(()-> new UserNotFoundException("User not found with id " + userid));
		
		if(user != null)
		{
			// Fetch and return tasks for the given user ID
			return taskRepo.getAllUserTask(userid);
		}
		else 
		{
			return null;
		}
	}

	@Override
	public Task updateTask(Long taskId, Task updatedTask) 
	{
		// Retrieve the task to be updated, throw an exception if not found
		Task extTask = taskRepo.findById(taskId).orElseThrow(()->new TaskNotFoundException("Task with id not found " +taskId));
		if(extTask!=null)
		{
			// Update the last modified timestamp
			extTask.setLastUdateDateTime(LocalDateTime.now());
			// Update task details
			extTask.setDescription(updatedTask.getDescription());
			extTask.setDueDate(updatedTask.getDueDate());
			extTask.setPriority(updatedTask.getPriority());
			extTask.setStatus(updatedTask.getStatus());
			extTask.setTitle(updatedTask.getTitle());
			// Save and return the updated task
			taskRepo.save(extTask);
			return extTask;
		}
		else
		{
			 throw new TaskNotFoundException("Task with id not found " +taskId);
		}
	}

	@Override
	public String deleteTask(Long taskId) 
	{
		// Retrieve the task to be deleted, throw an exception if not found
		Task extTask = taskRepo.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task with id not found " +taskId));
		if(extTask!=null)
		{
			// Delete the task and return a success message
			taskRepo.delete(extTask);
			return "Task deleted Sucessfully" + taskId;
		}
		else 
		{
			return "Task cannot be deleted with id " + taskId;
		}
	}

	@Override
	public Task getTaskById(Long taskId) 
	{
		// Retrieve the task by ID, throw an exception if not found
		Task extTask = taskRepo.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task with id not found " +taskId));
		if(extTask!=null)
		{
			return extTask; 
		}
		else 
		{
			throw new TaskNotFoundException("Task with id not found " + taskId);
		}
	}

	@Override
	public List<Task> getAllTasks() 
	{
		// Fetch all tasks from the repository
		List<Task> extAllTask = taskRepo.findAll();
		if(extAllTask!=null)
		{
			return extAllTask; 
		}
		else 
		{
			throw new TaskNotFoundException("There are no task available currently");
		}
	}

	@Override
	public List<Task> getAllUsersTasksServiceImpl(Long userId) 
	{
		// Retrieve all tasks associated with the given user ID
		List<Task> userTasks = taskRepo.getAllUserTask(userId);
		return userTasks;
	}

	@Override
	public Task updateTaskStatus(Long taskId, Task.Status status)
	{
		Task extTask = taskRepo.findById(taskId).orElseThrow(()-> 
			new TaskNotFoundException( "task not found with id " + taskId));
		
		
		if (status != null) 
		{
	        switch (status) 
	        {
	            case Task.Status.PENDING :
	                extTask.setStatus(Status.PENDING);
	                break;
	            case COMPLETED:
	                extTask.setStatus(Status.COMPLETED);
	                break;
	            case INPROCESS:
	                extTask.setStatus(Status.INPROCESS);
	                break;
	            case DISCARDED:
	                extTask.setStatus(Status.DISCARDED);
	                break;
	            default:
	                throw new InputMismatchException("Invalid status provided");
	        }

	        
	        taskRepo.save(extTask);
	        return extTask;
	        
		}
		else
		{
			throw new TaskNotFoundException( "task not found with id " + taskId);
		}

	}	
	
}
