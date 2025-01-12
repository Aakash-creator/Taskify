package com.tasify.serviceInterface;

import java.util.List;

import com.tasify.entity.Task;
import com.tasify.entity.TaskAssignment;
import com.tasify.entity.User;

public interface ITaskAssignmentService 
{
	
	public Task assignTaskToUser(Long taskId, Long userId);
    
	public boolean removeTaskAssignment(Long taskId, Long userId);
    
//    List<Task> getAssignedTasksByUserId(int userId);
    
	public List<User> getUsersAssignedToTask(Long taskId);
    
    public boolean isTaskAssignedToUser(Long taskId, Long userId);
    
//    void updateTaskAssignment(int taskId, int userId, AssignmentDetails details);
    
    public String unassignAllTasksFromUser(Long userId);
    
    public List<TaskAssignment> getAllTaskAssignments(Long userId);
    
    public TaskAssignment getAssignmentDetails(Long taskId, Long userId);
    
    public Integer countTasksAssignedToUser(Long userId);

    public TaskAssignment updateTaskAssignmentStatusByUser(long taskAssignmentId, long userId, String status);
    
}