package com.tasify.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tasify.entity.TaskComment;

public interface ITaskCommentRepo extends JpaRepository<TaskComment, Long> 
{
	 // Custom query to find comments by userId
	@Query("SELECT t FROM TaskComment t WHERE t.userId = :userId")
	List<TaskComment> findByUserId(Long userId);


    // Custom query to find comments by taskId
    @Query("SELECT t FROM TaskComment t WHERE t.taskId = :taskId")
    List<TaskComment> findByTaskId(Long taskId);
}
