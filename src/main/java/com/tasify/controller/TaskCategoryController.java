package com.tasify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasify.entity.Task;
import com.tasify.entity.TaskCategory;
import com.tasify.serviceInterface.ITaskCategoryService;

@RestController
public class TaskCategoryController 
{
	@Autowired
	private ITaskCategoryService taskCategoryService ;
	
	// POST - Create a new TaskCategory
	@PostMapping("/createtaskcategory/{taskId}")
	public ResponseEntity<Task> createTaskCategory(@PathVariable Long taskId, 
			@RequestBody TaskCategory taskCategory)
	{	
		Task newTaskCat = taskCategoryService.createCategoryForTask(taskId, taskCategory);
		return new ResponseEntity<Task>(newTaskCat, HttpStatus.OK);
		
	}
	
	 // GET - Get all TaskCategories
    @GetMapping("/taskcategories/all")
    public ResponseEntity<List<TaskCategory>> getAllTaskCategories()
    {
        List<TaskCategory> taskCategories = taskCategoryService.getAllTaskCategories();
        return new ResponseEntity<>(taskCategories, HttpStatus.OK);
    }

    // GET - Get TaskCategory by name
    @GetMapping("/taskcategoriesbyname/{name}")
    public ResponseEntity<TaskCategory> findCategoryByName(@PathVariable String name)
    {
        TaskCategory taskCategory = taskCategoryService.findByName(name);
        return new ResponseEntity<>(taskCategory, HttpStatus.OK);
    }

    // PUT - Update TaskCategory
    @PutMapping("/taskcategory/update/{id}")
    public ResponseEntity<TaskCategory> updateTaskCategory(@PathVariable Long id, @RequestBody TaskCategory updatedTaskCategory)
    {
        TaskCategory updatedCategory = taskCategoryService.updateTaskCategory(id, updatedTaskCategory);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // DELETE - Remove a Task from a TaskCategory
    @DeleteMapping("/deletetask/{taskCategoryId}/{taskId}")
    public ResponseEntity<String> removeTaskFromTaskCategory(@PathVariable Long taskCategoryId, @PathVariable Long taskId)
    {
        taskCategoryService.removeTaskFromTaskCategory(taskCategoryId, taskId);
        String res = "taskcategory deleted with id " + taskCategoryId;
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

}
