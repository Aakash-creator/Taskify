package com.tasify.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user_main")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotBlank(message = "This is required ")
//	@UniqueElements
	private String userName;
	
	@Column(unique = true)
//	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
	private String email;
	
	@NotBlank(message = "This is required")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public enum Role
	{
		ADMIN,
		USER
	}
	
//	@JsonManagedReference
	@ElementCollection
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "userId"
			)
	private List<Task> tasks = new ArrayList<Task>();
	
//	@JsonManagedReference
	@ElementCollection
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "user"
			)
	private List<TaskAssignment> taskAssignments = new ArrayList<>();
	
	@ElementCollection
	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "userId"
			)
	private List<TaskComment> taskComments = new ArrayList<>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String  userName, String email, @NotBlank(message = "This is required") String password,
			@NotBlank Role role, List<Task> tasks, List<TaskAssignment> taskAssignments,
			List<TaskComment> taskComments) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.tasks = tasks;
		this.taskAssignments = taskAssignments;
		this.taskComments = taskComments;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<TaskAssignment> getTaskAssignments() {
		return taskAssignments;
	}

	public void setTaskAssignments(List<TaskAssignment> taskAssignments) {
		this.taskAssignments = taskAssignments;
	}

	public List<TaskComment> getTaskComments() {
		return taskComments;
	}

	public void setTaskComments(List<TaskComment> taskComments) {
		this.taskComments = taskComments;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + ", role="
				+ role + ", tasks=" + tasks + ", taskAssignments=" + taskAssignments + ", taskComments=" + taskComments
				+ "]";
	}

	
}