package com.tasify.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name =  "taskhistory_main")
public class TaskHistory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "task_id",referencedColumnName = "id")
	private Task task;
//	A history entry belongs to one task
	
	private String changeDescription;
	
	private String changedBy;
	
	private LocalDateTime changeDate;

	public TaskHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskHistory(Long id, Task task, String changeDescription, String changedBy, LocalDateTime changeDate) {
		super();
		this.id = id;
		this.task = task;
		this.changeDescription = changeDescription;
		this.changedBy = changedBy;
		this.changeDate = changeDate;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public LocalDateTime getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDateTime changeDate) {
		this.changeDate = changeDate;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "TaskHistory [id=" + id + ", task=" + task + ", changeDescription=" + changeDescription + ", changedBy="
				+ changedBy + ", changeDate=" + changeDate + "]";
	}
	
}
