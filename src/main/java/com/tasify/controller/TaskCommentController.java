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
import com.tasify.entity.TaskComment;
import com.tasify.serviceInterface.ITaskCommentService;

@RestController
public class TaskCommentController 
{
	@Autowired
	private ITaskCommentService taskCommentService;
	
	// Endpoint to create a comment for a task
    @PostMapping("/createcomment/{taskId}/{userId}")
    public ResponseEntity<Task> createCommentForTask(
            @PathVariable Long taskId,
            @PathVariable Long userId, 
            @RequestBody TaskComment comment) {    
        Task newTaskComment = taskCommentService.addCommentToTask(taskId, userId, comment);
        
        return new ResponseEntity<>(newTaskComment, HttpStatus.OK);
    }

    // Endpoint to update a comment
    @PutMapping("/updatecomment/{commentId}")
    public ResponseEntity<TaskComment> updateComment(
            @PathVariable Long commentId, 
            @RequestBody String newComment) 
    {
        TaskComment updatedComment = taskCommentService.updateComment(commentId, newComment);
        
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // Endpoint to delete a comment
    @DeleteMapping("/deletecomment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) 
    {
        taskCommentService.deleteComment(commentId);
        String res = "Comment deleted with id " + commentId;
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    // Endpoint to get all comments by a specific user
    @GetMapping("/commentsbyuser/{userId}")
    public ResponseEntity<List<TaskComment>> getUsersAllComments(@PathVariable Long userId) 
    {
        List<TaskComment> comments = taskCommentService.getUsersAllComments(userId);
        
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Endpoint to get all comments for a specific task
    @GetMapping("/commentsbytask/{taskId}")
    public ResponseEntity<List<TaskComment>> getAllCommentsOfTask(@PathVariable Long taskId)
    {
        List<TaskComment> comments = taskCommentService.getAllCommentsOfTask(taskId);
        
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Endpoint to get all comments by a user
    @GetMapping("/allcomments/{userId}")
    public ResponseEntity<List<TaskComment>> getAllCommentsByUser(@PathVariable Long userId) 
    {
        List<TaskComment> comments = taskCommentService.getAllCommentsByUser(userId);
        
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
	
}
