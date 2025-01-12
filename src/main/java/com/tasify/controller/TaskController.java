package com.tasify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasify.entity.Task;
import com.tasify.entity.User;
import com.tasify.serviceInterface.ITaskService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TaskController 
{
	@Autowired
	private ITaskService taskService;
	
	@Operation(summary = "POST CREATE NEW TASK")
	@PostMapping("/createtask")
	public ResponseEntity<Task> createTask(@RequestBody Task task)
	{
		Task newTask = taskService.createTask(task);
		return new ResponseEntity<Task>(newTask,HttpStatus.OK);
	}
	
	@GetMapping("/gettask")
	public ResponseEntity<List<Task>> getAllTask()
	{
		 List<Task> newTask = taskService.getAllTasks();
		return new ResponseEntity<List<Task>>(newTask,HttpStatus.OK);
	}
	
	@GetMapping("/gettaskbyid/{userid}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long userid)
	{
		Task newTask = taskService.getTaskById(userid);
		return new ResponseEntity<Task>(newTask,HttpStatus.OK);
	}
	
	@GetMapping("/getallusertask/{userid}")
	public ResponseEntity<?> getAllUserTaskById(@PathVariable Long userid)
	{
		List<Task> newTask = taskService.getTasksByUserId(userid);
		if(newTask == null)
		{
			
			String errMsg = new String("User with id not found or tasks not found ");
			return new ResponseEntity<>(errMsg,HttpStatus.NOT_FOUND); 
		}
		else 
		{
			return new ResponseEntity<List<Task>>(newTask,HttpStatus.OK);
			
		}
		
	}
	
	@PutMapping("/updatetask/{taskid}")
	public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable Long taskid)
	{
		Task newTask = taskService.updateTask(taskid,task);
		return new ResponseEntity<Task>(newTask,HttpStatus.OK);
	}
	
	// Endpoint to get all tasks for a user
    @GetMapping("/allusertasks/{userId}")
    public ResponseEntity<List<Task>> getAllTasksForUser(@PathVariable Long userId) 
    {
        List<Task> tasks = taskService.getAllUsersTasksServiceImpl(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Endpoint to update task status
    @PatchMapping("/updatetask/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestBody Task.Status taskstatus) 
    {
    	
    	
        Task updatedTask = taskService.updateTaskStatus(taskId, taskstatus) ;
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
	
}
