package com.tasify.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tasify.entity.TaskAssignment;

public interface ITaskAssignmentRepo extends JpaRepository<TaskAssignment, Long> 
{
	@Modifying
	@Query(value = "INSERT INTO taskassignment_main (task_id, user_id, status) VALUES (:taskId, :userId, :status)", nativeQuery = true)
	void assignTaskToUser(Long taskId,  Long userId, TaskAssignment.Status status);

	@Query("SELECT ta FROM TaskAssignment ta WHERE ta.task.id = :taskId AND ta.user.id = :userId")
	Optional<TaskAssignment> findByTaskIdAndUserId(Long taskId, Long userId);

	@Query("SELECT COUNT(ta) FROM TaskAssignment ta WHERE ta.user.id = :userId")
	Integer countByUserId( Long userId);
	
	@Modifying
	@Query("DELETE FROM TaskAssignment ta WHERE ta.user.id = :userId")
	Integer deleteAllByUserId( Long userId);
	
	@Query("SELECT COUNT(ta) > 0 FROM TaskAssignment ta WHERE ta.task.id = :taskId AND ta.user.id = :userId")
	boolean existsByTaskIdAndUserId( Long taskId, Long userId);

	@Query("SELECT t FROM TaskAssignment t WHERE t.user.id = :userId")
	List<TaskAssignment> findTaskAssignedByUserId(Long userId);


}
