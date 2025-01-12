package com.tasify.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tasify.entity.TaskCategory;

public interface ITaskCategoryRepo extends JpaRepository<TaskCategory, Long> 
{
	@Query("SELECT t FROM TaskCategory t WHERE t.name = :name")
	Optional<TaskCategory> findByName(String name);

}
