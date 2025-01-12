package com.tasify.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "taskcomment_main")
public class TaskComment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@ManyToOne
	@JoinColumn(name = "task_id",referencedColumnName = "id")
	private Long taskId;
	
//	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private Long userId;
	
	private String comment;
	
	private LocalDateTime createDateTime;

	public TaskComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskComment(Long id, Long taskId, Long userId, String comment, LocalDateTime createDateTime) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.userId = userId;
		this.comment = comment;
		this.createDateTime = createDateTime;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "TaskComment [id=" + id + ", taskId=" + taskId + ", userId=" + userId + ", comment=" + comment
				+ ", createDateTime=" + createDateTime + "]";
	}
	
}
