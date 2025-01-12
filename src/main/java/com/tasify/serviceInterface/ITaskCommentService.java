package com.tasify.serviceInterface;

import java.util.List;

import com.tasify.entity.Task;
import com.tasify.entity.TaskComment;

public interface ITaskCommentService 
{
	public Task addCommentToTask(Long taskId, Long userId, TaskComment comment);
	
	public List<TaskComment> getAllCommentsOfTask(Long taskId);
	public List<TaskComment> getAllCommentsByUser(Long userId);
	
	public TaskComment updateComment(Long commentId, String newComment);
	public void deleteComment(Long commentId);
	
	List<TaskComment> getUsersAllComments(Long userId);
	

//	List<Task> getUserTaskAssignments(Long userId);
//	Task-related operations
//	String assignTaskToUser(Long userId, Long taskId);
//	String removeTaskFromUser(Long userId, Long taskId);
//	Tag management
//	Task addTagToTask(Long taskId, Long tagId);
//	Task removeTagFromTask(Long taskId, Long tagId);
//	History operations
//	List<TaskHistory> getTaskHistory(Long taskId);


}
