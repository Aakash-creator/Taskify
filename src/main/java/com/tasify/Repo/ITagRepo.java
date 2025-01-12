package com.tasify.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tasify.entity.Tag;
import com.tasify.entity.Task;

public interface ITagRepo extends JpaRepository<Tag, Long>
{
	
	@Query("SELECT t FROM Tag t WHERE t.name = :name")
	Optional<Tag> findByName(String name);
	
	@Query("SELECT t FROM Task t JOIN t.tags tag WHERE tag.name = :tagName")
    List<Task> getAllTasksByTagName(String tagName);
	
	
//	  // Query to find a tag by its name
//    @Query("SELECT t FROM Tag t WHERE t.name = :name")
//    Tag findTagByName(@Param("name") String name);
//
//    // Query to find tags associated with a specific task ID
//    @Query("SELECT t FROM Tag t JOIN t.tasks task WHERE task.id = :taskId")
//    List<Tag> findTagsByTaskId(@Param("taskId") Long taskId);
}
