package com.tasify.serviceInterface;

import java.util.List;

import com.tasify.entity.Task;
import com.tasify.entity.TaskCategory;

public interface ITaskCategoryService 
{
	// TaskCategoryService
	public Task addTaskCategoryToTask(Long taskId, Long categoryId);
	public Task createCategoryForTask(Long taskId, TaskCategory category);
	public TaskCategory findByName(String name);
	
	// GET methods
	public List<TaskCategory> getAllTaskCategories();

	// PUT methods
	public TaskCategory updateTaskCategory(Long id, TaskCategory updatedTaskCategory);

	// DELETE methods
	public void removeTaskFromTaskCategory(Long taskCategoryId, Long taskId);

}
