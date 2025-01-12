package com.tasify.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tag_main")
public class Tag 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
//	@JsonBackReference
//	@JsonIgnore
	@ManyToMany(
			mappedBy = "tags",
			cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	private List<Task> tasks = new ArrayList<Task>();

	
	
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(Long id, String name, List<Task> tasks) {
		super();
		this.id = id;
		this.name = name;
		this.tasks = tasks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Tag [id=" + id + ", name=" + name + ", tasks=" + tasks + "]";
	}
	
	
	
}
