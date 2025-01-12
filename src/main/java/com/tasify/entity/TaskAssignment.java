package com.tasify.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "taskassignment_main")
public class TaskAssignment 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private Status status;
	
//	private Long assignedBy;
	
	public enum Status
	{
		ACCEPTED,
		DECLINED,
		PEDNING
	}

	public TaskAssignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskAssignment(Long id, Task task, User user, Status status
//			, String assignedBy
			)
	{
		super();
		this.id = id;
		this.task = task;
		this.user = user;
		this.status = status;
//		this.assignedBy = assignedBy;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

//	public String getAssignedBy() {
//		return assignedBy;
//	}
//
//	public void setAssignedBy(String assignedBy) {
//		this.assignedBy = assignedBy;
//	}

	public Long getId() {
		return id;
	}

	
}
