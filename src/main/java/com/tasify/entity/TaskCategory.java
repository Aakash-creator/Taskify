package com.tasify.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "taskcategory_main")
public class TaskCategory 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
//	@JsonBackReference
	@OneToMany(
			mappedBy = "id",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<Task> tasks = new ArrayList<Task>();

	public TaskCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskCategory(Long id, String name, String description, List<Task> tasks) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.tasks = tasks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "TaskCategory [id=" + id + ", name=" + name + ", description=" + description + ", tasks=" + tasks + "]";
	}
	
	
}
