package com.tasify.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasify.Exceptions.TaskCommentNotFoundException;
import com.tasify.Exceptions.TaskNotFoundException;
import com.tasify.Exceptions.UserNotFoundException;
import com.tasify.Repo.ITaskCommentRepo;
import com.tasify.Repo.ITaskRepo;
import com.tasify.Repo.IUserRepo;
import com.tasify.entity.Task;
import com.tasify.entity.TaskComment;
import com.tasify.entity.User;
import com.tasify.serviceInterface.ITaskCommentService;

@Service
public class TaskCommentService implements ITaskCommentService 
{

	@Autowired
	private ITaskCommentRepo taskCommentRepo;
	
	@Autowired
	private ITaskRepo taskRepo;
	
	@Autowired
	private IUserRepo userRepo;
	
	
	@Override
	public Task addCommentToTask(Long taskId, Long userId, TaskComment comment) {	

	    // Fetch the task by ID to ensure the comment is only added to an existing task
	    Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));

	    // Fetch the user by ID to ensure the comment is only added by an existing user
	    User user = userRepo.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

	    // Set the task ID and user ID in the comment to link it to the task and user
	    comment.setTaskId(task.getId());
	    comment.setUserId(user.getId());
	    // Set the current date and time for the comment
	    comment.setCreateDateTime(LocalDateTime.now());
	    
	    // Save the comment (it will be persisted because of the cascade type set in Task entity)
	    taskCommentRepo.save(comment);

	    // Return the task with the newly added comment
	    return task;
	}

	@Override
	public TaskComment updateComment(Long commentId, String newComment) {
	    // Retrieve the existing comment by its ID, throw exception if not found
	    TaskComment extTaskComm = taskCommentRepo.findById(commentId).orElseThrow(() -> 
	        new TaskCommentNotFoundException("Task comment not found with id " + commentId));
	    
	    // If comment exists, update the comment text
	    if (extTaskComm != null) {
	        extTaskComm.setComment(newComment);
	        // Save the updated comment to the repository
	        taskCommentRepo.save(extTaskComm);
	        return extTaskComm;
	    } else {
	        // If the comment was not found, throw an exception
	        throw new TaskCommentNotFoundException("Task comment not found with id " + commentId);
	    }
	}

	@Override
	public void deleteComment(Long commentId) {
	    // Check if the comment exists by its ID, throw exception if not found
	    taskCommentRepo.findById(commentId).orElseThrow(() -> 
	        new TaskCommentNotFoundException("Task comment not found with id " + commentId));
	    
	    // Delete the comment after verifying it exists
	    taskCommentRepo.deleteById(commentId);
	}

	@Override
	public List<TaskComment> getUsersAllComments(Long userId) {
	    // Check if user exists, then return all their comments
	    List<TaskComment> comments = taskCommentRepo.findByUserId(userId);
	    if (comments == null || comments.isEmpty()) {
	        // If no comments are found for the user, throw an exception
	        throw new TaskCommentNotFoundException("No comments found for user with id " + userId);
	    }
	    // Return the list of comments if found
	    return comments;
	}

	@Override
	public List<TaskComment> getAllCommentsOfTask(Long taskId) {
	    // Retrieve all comments for a specific task
	    List<TaskComment> comments = taskCommentRepo.findByTaskId(taskId);
	    if (comments == null || comments.isEmpty()) {
	        // If no comments are found for the task, throw an exception
	        throw new TaskCommentNotFoundException("No comments found for task with id " + taskId);
	    }
	    // Return the list of comments for the task
	    return comments;
	}

	@Override
	public List<TaskComment> getAllCommentsByUser(Long userId) {
	    // Retrieve all comments for the user
	    List<TaskComment> comments = taskCommentRepo.findByUserId(userId);
	    if (comments == null || comments.isEmpty()) {
	        // If no comments are found for the user, throw an exception
	        throw new TaskCommentNotFoundException("No comments found for user with id " + userId);
	    }
	    // Return the list of comments for the user
	    return comments;
	}

}
