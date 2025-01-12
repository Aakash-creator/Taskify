package com.tasify.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_main")
public class Task 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;
	
	private Status status;
	
	private String priority;
	
	private LocalDate dueDate;
	
	private LocalDateTime createDateTime;
	
	private LocalDateTime lastUdateDateTime;
	
//	@JsonBackReference
//	@JsonIgnore
	@JoinColumn(name = "user_id")
	private Long userId;
	
	
	@ManyToMany(fetch = FetchType.EAGER, 
			cascade = {CascadeType.MERGE,
					CascadeType.PERSIST}
			)
	@JoinTable(
			name = "task_tag",
			joinColumns = {
					@JoinColumn(name = "tags_id",
					referencedColumnName = "id",
					updatable = true)
			},
			inverseJoinColumns = {
					@JoinColumn(name = "task_id",
					referencedColumnName = "id",
					updatable = true),
			}
			)
	private Set<Tag> tags = new HashSet<>();
	
	
//	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "taskCategory_id")
	private TaskCategory category;
	
//	@JsonManagedReference
	@OneToMany(
			mappedBy = "taskId",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<TaskComment> comments = new ArrayList<>();
	
//	@JsonManagedReference
	@OneToMany(
			mappedBy = "task",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<TaskHistory> history = new ArrayList<>();
	
	
	public enum Status
	{
		PENDING,
		COMPLETED,
		INPROCESS,
		DISCARDED,
	}


	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(Long id, String title, String description, Status status, String priority, LocalDate dueDate,
			LocalDateTime createDateTime, LocalDateTime lastUdateDateTime, Long userId, Set<Tag> tags,
			TaskCategory category, List<TaskComment> comments, List<TaskHistory> history) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.dueDate = dueDate;
		this.createDateTime = createDateTime;
		this.lastUdateDateTime = lastUdateDateTime;
		this.userId = userId;
		this.tags = tags;
		this.category = category;
		this.comments = comments;
		this.history = history;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}


	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}


	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}


	public LocalDateTime getLastUdateDateTime() {
		return lastUdateDateTime;
	}


	public void setLastUdateDateTime(LocalDateTime lastUdateDateTime) {
		this.lastUdateDateTime = lastUdateDateTime;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<Tag> getTags() {
		return tags;
	}


	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}


	public TaskCategory getCategory() {
		return category;
	}


	public void setCategory(TaskCategory category) {
		this.category = category;
	}


	public List<TaskComment> getComments() {
		return comments;
	}


	public void setComments(List<TaskComment> comments) {
		this.comments = comments;
	}


	public List<TaskHistory> getHistory() {
		return history;
	}


	public void setHistory(List<TaskHistory> history) {
		this.history = history;
	}


	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", priority=" + priority + ", dueDate=" + dueDate + ", createDateTime=" + createDateTime
				+ ", lastUdateDateTime=" + lastUdateDateTime + ", userId=" + userId + ", tags=" + tags + ", category="
				+ category + ", comments=" + comments + ", history=" + history + "]";
	}

	
	
}
