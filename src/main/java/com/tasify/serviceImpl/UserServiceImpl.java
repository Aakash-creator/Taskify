package com.tasify.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasify.Exceptions.UserNotFoundException;
import com.tasify.Repo.IUserRepo;
import com.tasify.entity.User;
import com.tasify.entity.User.Role;
import com.tasify.serviceInterface.IUserService;

import jakarta.transaction.Transactional;
@Service
public class UserServiceImpl implements IUserService 
{
	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private MailNotificationSender mailSender;
	
	@Override
	public User createUser(User user) {
	    // Check if a user with the same email or username already exists
	    if (userRepo.findByEmail(user.getEmail()) != null) {
	        throw new IllegalArgumentException("User with this email or username already exists");
	    } else {
	        // Set default role for the new user
	        user.setRole(Role.USER);
	        
	        // Send email on user creation
	        mailSender.sendmail(
//	        		user.getEmail()
	            "bucksbunny148@gmail.com", // mail for testing purpose 
	            "Account created successfully: " + user.getUserName(), 
	            "Welcome to Task Manager! We hope you would love being here!!");
	        
	        // Save the new user
	        return userRepo.save(user);
	    }
	}

	@Override
	public User updateUserName(Long id, String username) {
	    User userAvail = userRepo.findById(id)
	                              .orElseThrow(() -> new UserNotFoundException("User not found with ID " + id));
	    
	    if (userAvail != null) {
	        String oldUserName = userAvail.getUserName();
	        userAvail.setUserName(username);
	        
	        // Send email on username change
	        mailSender.sendmail(
//	        		user.getEmail()
	            "bucksbunny148@gmail.com", // mail for testing purpose
	            "Username updated successfully: " + oldUserName + " -> " + username, 
	            "Your username has been updated successfully. Welcome to Task Manager with your new username!");
	        
	        userRepo.save(userAvail);
	        return userAvail;
	    } else {
	        throw new UserNotFoundException("User not found with ID " + id);
	    }
	}

	@Transactional
	@Override
	public String deleteUser(Long id) {
	    User user = userRepo.findById(id)
	                        .orElseThrow(() -> new UserNotFoundException("User not found with ID " + id));
	    
	    if (user != null) {
	        // Send email on user deletion
	        mailSender.sendmail(
//	        		user.getEmail()
	            "bucksbunny148@gmail.com", // mail for testing purpose
	            "Account deletion successful for: " + user.getUserName(),
	            "Your account has been deleted from Task Manager. We're sorry to see you go.");
	        
	        userRepo.delete(user);
	        return "User deleted successfully";
	    }
	    return "User does not exist";
	}


	@Override
	public User getUserById(Long id) 
	{
		return userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("User not found with id " + id));
		
	}

	@Override
	public User getUserByEmail(String email) 
	{
		User extUser = userRepo.findByEmail(email);
		return extUser;
	}

	@Override
	public User getUserByUsername(String username) 
	{
		User extUser = userRepo.findByUsername(username);
		return extUser;
	}


}
