package com.tasify.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tasify.entity.User;

public interface IUserRepo extends JpaRepository<User, Long> 
{
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.userName = :username")
	public User findByUsername(String username);

	
	
}
