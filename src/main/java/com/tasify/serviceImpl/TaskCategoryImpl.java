package com.tasify.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasify.Exceptions.TaskCategoryNotFoundException;
import com.tasify.Exceptions.TaskNotFoundException;
import com.tasify.Repo.ITaskCategoryRepo;
import com.tasify.Repo.ITaskRepo;
import com.tasify.entity.Task;
import com.tasify.entity.TaskCategory;
import com.tasify.serviceInterface.ITaskCategoryService;

@Service
public class TaskCategoryImpl implements ITaskCategoryService 
{

	@Autowired
	private ITaskCategoryRepo taskCategoryRepo;
	

	@Autowired
	private ITaskRepo taskRepo;
	
	@Override
	public Task addTaskCategoryToTask(Long taskId, Long categoryId) 
	{
		
		return null;
	}

	@Override
	public Task createCategoryForTask(Long taskId, TaskCategory category) 
	{
		// Fetch the task by ID to ensure the category is only added with a task
	    Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));

	    // Check if a TaskCategory with the same name already exists (case-sensitive check)
	    Optional<TaskCategory> existingCategoryOptional = taskCategoryRepo.findByName(category.getName());

	    TaskCategory categoryToUse;
	    if (existingCategoryOptional.isPresent()) {
	        // If the category exists, use the existing one and get its id
	        categoryToUse = existingCategoryOptional.get();
	        // Set the category id in the task
	        task.setCategory(categoryToUse);
	    } else {
	        // If the category doesn't exist, create a new one and save it
	        categoryToUse = taskCategoryRepo.save(category);
	        // Set the new category id in the task
	        task.setCategory(categoryToUse);
	    }

	    // Save the task with the assigned category
	    task = taskRepo.save(task);

	    // Return the task with the assigned category
	    return task;
	
	}

	@Override
	public TaskCategory findByName(String name) 
	{	
		return taskCategoryRepo.findByName(name).orElseThrow(() -> 
		new RuntimeException("TaskCategory with name " + name + " not found"));
		
	}

//	************************
	
	@Override
	public List<TaskCategory> getAllTaskCategories() 
	{
	    // Retrieve all TaskCategories from the database
	    return taskCategoryRepo.findAll();
	}

	@Override
	public TaskCategory updateTaskCategory(Long id, TaskCategory updatedTaskCategory) 
	{
	    // Check if TaskCategory exists
	    Optional<TaskCategory> existingTaskCategory = taskCategoryRepo.findById(id);
	    
	    if (existingTaskCategory.isPresent()) {
	        // Update the existing TaskCategory with new data
	        TaskCategory taskCategory = existingTaskCategory.get();
	        taskCategory.setName(updatedTaskCategory.getName());
	        taskCategory.setDescription(updatedTaskCategory.getDescription());
	        
	        // Save and return the updated TaskCategory
	        return taskCategoryRepo.save(taskCategory);
	    } else {
	        // Throw an exception or return null if not found
	        throw new TaskCategoryNotFoundException("TaskCategory not found with id: " + id);
	    }
	}

	@Override
	public void removeTaskFromTaskCategory(Long taskCategoryId, Long taskId) 
	{
	    // Find the TaskCategory by ID
	    Optional<TaskCategory> taskCategoryOptional = taskCategoryRepo.findById(taskCategoryId);
	    
	    if (taskCategoryOptional.isPresent()) {
	        TaskCategory taskCategory = taskCategoryOptional.get();
	        
	        // Find the Task by ID and remove it from the TaskCategory's tasks list
	        Optional<Task> taskOptional = taskRepo.findById(taskId);
	        
	        if (taskOptional.isPresent()) {
	            Task task = taskOptional.get();
	            taskCategory.getTasks().remove(task);  // Remove the task from the category
	            
	            // Save the updated TaskCategory
	            taskCategoryRepo.save(taskCategory);
	        } else {
	            // Throw an exception or handle task not found
	            throw new TaskCategoryNotFoundException("Task not found with id: " + taskId);
	        }
	    } else {
	        // Throw an exception or handle task category not found
	        throw new TaskCategoryNotFoundException("TaskCategory not found with id: " + taskCategoryId);
	    }
	}

}
