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
import org.springframework.web.bind.annotation.RestController;

import com.tasify.entity.Task;
import com.tasify.entity.TaskAssignment;
import com.tasify.entity.User;
import com.tasify.serviceInterface.ITaskAssignmentService;

@RestController
public class TaskAssignmentController 
{
	@Autowired
	private ITaskAssignmentService taskAssignmentService;
	
	@PostMapping("/assigntask/{userid}/{taskid}")
	public ResponseEntity<Task> assignTaskToUser(
			@PathVariable Long taskid, 
			@PathVariable Long userid)
	{
		
		Task taskAssigned = taskAssignmentService.assignTaskToUser(taskid, userid);
		
		return new ResponseEntity<Task>(taskAssigned,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/remove/{taskId}/{userId}")
    public ResponseEntity<String> removeTaskAssignment(@PathVariable Long taskId, @PathVariable Long userId) 
    {
        boolean removed = taskAssignmentService.removeTaskAssignment(taskId, userId);
        return new ResponseEntity<>(removed ? "Task assignment removed" : "Task assignment not found", HttpStatus.OK);
    }

    @GetMapping("/users/{taskId}")
    public ResponseEntity<List<User>> getUsersAssignedToTask(@PathVariable Long taskId) 
    {
        List<User> users = taskAssignmentService.getUsersAssignedToTask(taskId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/check-assignment/{taskId}/{userId}")
    public ResponseEntity<Boolean> isTaskAssignedToUser(@PathVariable Long taskId, @PathVariable Long userId) 
    {
        boolean isAssigned = taskAssignmentService.isTaskAssignedToUser(taskId, userId);
        return new ResponseEntity<>(isAssigned, HttpStatus.OK);
    }

    @DeleteMapping("/unassign-all/{userId}")
    public ResponseEntity<String> unassignAllTasksFromUser(@PathVariable Long userId) 
    {
        String result = taskAssignmentService.unassignAllTasksFromUser(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getalltaskassignedtouser/{userid}")
    public ResponseEntity<List<TaskAssignment>> getAllTaskAssignments(@PathVariable Long userid) 
    {
        List<TaskAssignment> assignments = taskAssignmentService.getAllTaskAssignments(userid);
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    @GetMapping("/details/{taskId}/{userId}")
    public ResponseEntity<TaskAssignment> getAssignmentDetails(@PathVariable Long taskId, @PathVariable Long userId) 
    {
        TaskAssignment details = taskAssignmentService.getAssignmentDetails(taskId, userId);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Integer> countTasksAssignedToUser(@PathVariable Long userId) 
    {
        Integer count = taskAssignmentService.countTasksAssignedToUser(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    
    @PutMapping("/{taskAssignmentId}/status/{userId}/{status}")
    public TaskAssignment updateTaskAssignmentStatusByUser(
            @PathVariable long taskAssignmentId,
            @PathVariable long userId,
            @PathVariable String status)
    {
        return taskAssignmentService.updateTaskAssignmentStatusByUser(taskAssignmentId, userId, status);
    }
    
}
