package com.tasify.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasify.Exceptions.TaskAssignmentNotFoundException;
import com.tasify.Exceptions.TaskNotFoundException;
import com.tasify.Exceptions.UserNotFoundException;
import com.tasify.Repo.ITaskAssignmentRepo;
import com.tasify.Repo.ITaskRepo;
import com.tasify.Repo.IUserRepo;
import com.tasify.entity.Task;
import com.tasify.entity.TaskAssignment;
import com.tasify.entity.User;
import com.tasify.serviceInterface.ITaskAssignmentService;

import jakarta.transaction.Transactional;

@Service
public class TaskAssignmentServiceImpl implements ITaskAssignmentService 
{

	@Autowired
	private ITaskAssignmentRepo taskAssignmentRepo;
	
	@Autowired
	private ITaskRepo taskRepo;
	
	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private MailNotificationSender mailSender;

	@Transactional
	@Override
	public Task assignTaskToUser(Long taskId, Long userId) 
	{
	    Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + taskId));

	    User user = userRepo.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

	    if (task != null && user != null) 
	    {
	        TaskAssignment taskAssignment = new TaskAssignment();
	        taskAssignment.setTask(task);
	        taskAssignment.setUser(user);
	        taskAssignment.setStatus(TaskAssignment.Status.PEDNING);
	        taskAssignmentRepo.save(taskAssignment);

	        TaskAssignment savedTA = taskAssignmentRepo.findByTaskIdAndUserId(taskId, userId).orElseThrow(() -> 
	        	new TaskAssignmentNotFoundException("Task assignment not found for taskId " + taskId + " and userId " + userId));

	        
	        // Construct the URL with parameters
	        String acceptUrl = String.format(
	            "http://localhost:8089/TaskManagerAPI/"+ savedTA.getId() + "/status/"+ userId + "/ACCEPTED", 
	            taskAssignment.getId(), userId
	        );
	        String declineUrl = String.format(
	        		"http://localhost:8089/TaskManagerAPI/"+ savedTA.getId() + "/status/"+ userId + "DECLINED", 
	            taskAssignment.getId(), userId
	        );

	        String emailBody = "You have been assigned to the task: " + task.getTitle() +
	                ".\n\nPlease click one of the links below to respond:\n" +
	                "Accept: " + acceptUrl + "\n" +
	                "Decline: " + declineUrl;

	        // Send email to the user
	        mailSender.sendmail
	        (
	        		"bucksbunny148@gmail.com",
//	            user.getEmail(), 
	            "Task Assigned: " + task.getTitle(), 
	            emailBody
	        );
	    }

	    return task;
	}

	@Transactional
	@Override
	public boolean removeTaskAssignment(Long taskId, Long userId)
	{
	    // Check if the task assignment exists for the given taskId and userId
	    boolean taskAssignmentExists = taskAssignmentRepo.existsByTaskIdAndUserId(taskId, userId);
	   
	    if (taskAssignmentExists)
	    {
	        // Fetch the task assignment to delete
	        TaskAssignment assignment = taskAssignmentRepo.findByTaskIdAndUserId(taskId, userId)
	                .orElseThrow(() -> new TaskAssignmentNotFoundException("Task assignment not found for taskId " + taskId + " and userId " + userId));

	        // Delete the task assignment
	        taskAssignmentRepo.deleteById(assignment.getId());

	        // Fetch the user for email notification
	        User user = userRepo.findById(userId)
	                .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
	        
	        Task reTask = taskRepo.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task not found for taskId " + taskId));

	        mailSender.sendmail(
		    		"bucksbunny148@gmail.com",
//		        user.getEmail(), // Send to the user's email
		        "You have been removed from Task :-" + reTask.getTitle() , 
		        "You have been removed from the task. If you have any questions, please contact the administrator."
		    );
	    } 
	    else
	    {
	        throw new TaskAssignmentNotFoundException("No task assignment found for taskId " + taskId + " and userId " + userId);
	    }

	    return true;
	}

	
    @Override
    public List<User> getUsersAssignedToTask(Long taskId) 
    {
        if (!taskRepo.existsById(taskId)) {
            throw new TaskNotFoundException("Task not found with id " + taskId);
        }
        return userRepo.findAll();
    }

    @Override
    public boolean isTaskAssignedToUser(Long taskId, Long userId) 
    {
        return taskAssignmentRepo.existsByTaskIdAndUserId(taskId, userId);
    }

    @Transactional
    @Override
    public String unassignAllTasksFromUser(Long userId) 
    {
        if (!userRepo.existsById(userId))
        {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        int count = taskAssignmentRepo.deleteAllByUserId(userId);
        return count + " task(s) unassigned from user with id " + userId;
    }

    @Override
    public List<TaskAssignment> getAllTaskAssignments(Long userId) 
    {
    	 return taskAssignmentRepo.findTaskAssignedByUserId(userId);
    }

    @Override
    public TaskAssignment getAssignmentDetails(Long taskId, Long userId) 
    {
        return taskAssignmentRepo.findByTaskIdAndUserId(taskId, userId)
                .orElseThrow(() -> new TaskAssignmentNotFoundException("Task assignment not found for taskId " + taskId + " and userId " + userId));
    }

    @Override
    public Integer countTasksAssignedToUser(Long userId)
    {
        if (!userRepo.existsById(userId)) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        return taskAssignmentRepo.countByUserId(userId);
    }
	
    public TaskAssignment updateTaskAssignmentStatusByUser(long taskAssignmentId, long userId, String status)
    {
        TaskAssignment taskAssignment = taskAssignmentRepo.findById(taskAssignmentId)
                .orElseThrow(() -> new IllegalArgumentException("TaskAssignment not found with ID: " + taskAssignmentId));

        if (taskAssignment.getUser().getId() != userId) {
            throw new SecurityException("User is not authorized to update this task assignment.");
        }

      // Assuming there is a status field.
        
        if (status != null)
        {
            switch (status) 
            {
                case "ACCEPTED":
                    taskAssignment.setStatus(TaskAssignment.Status.ACCEPTED);
                    break;
                case "DECLINED":
                    taskAssignment.setStatus(TaskAssignment.Status.DECLINED);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid status provided: " + status);
            }
        } 
        else
        {
            throw new IllegalArgumentException("Status cannot be null");
        }
        
        return taskAssignmentRepo.save(taskAssignment);
    }

}