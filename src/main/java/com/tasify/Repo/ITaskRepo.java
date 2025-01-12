package com.tasify.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tasify.entity.Task;

public interface ITaskRepo extends JpaRepository<Task, Long> 
{
//	// Filtering and querying
//	List<Task> getTasksByStatus(Task.Status status);
//
//	List<Task> getTasksByPriority(String priority);
//
//	List<Task> getTasksByDueDate(LocalDate dueDate);
//
	@Query("SELECT t FROM Task t WHERE t.userId = :userId")
	List<Task> getAllUserTask(Long userId);
	

//
//	List<Task> getTasksByCategory(Long categoryId);
//
//	// Utility methods
//	List<Task> getOverdueTasks();
//
//	List<Task> getTasksCreatedWithinDateRange(LocalDate startDate, LocalDate endDate);
}
