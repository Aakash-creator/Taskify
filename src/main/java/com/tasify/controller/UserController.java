package com.tasify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.tasify.entity.User;
import com.tasify.service.config.JwtTokenService;
import com.tasify.serviceInterface.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "UserController", description = "All related users CREATE, DELETE, ")
@RequestMapping("/auth")
public class UserController 
{
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenService Jwttoken;
	
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
	
	
	@PostMapping("/createuser")
	@Operation(summary = "POST Create user returns created user")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		   // Encode the password before saving
	    String encodedPassword = bcrypt.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    
	    // Create the user
	    User newUser = userService.createUser(user);
	    
	    // Return a ResponseEntity with the created user and appropriate status code
	    return new ResponseEntity<User>(newUser,HttpStatus.OK);
		
	}
	
	@PostMapping("/login")
	public String loginuser(@RequestBody User user) 
	{
		Authentication authenticated = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		
		if(authenticated.isAuthenticated()) 
		{
			
			return  Jwttoken.generateToken(user.getUserName());
		}
		return new String("Invalid credentails");
	}
	
	
	@GetMapping("/getuser/{userid}")
	@Operation(summary = "POST Create user returns created user")
	public ResponseEntity<User> getUser(@PathVariable Long userid)
	{
		User newUser = userService.getUserById(userid);
		return new ResponseEntity<User>(newUser,HttpStatus.OK);
		
	}
	
	@GetMapping("/home")
	@Operation(summary = "POST Create user returns created user")
	public ResponseEntity<String> getresult()
	{
		String newUser = "working this ";
		return new ResponseEntity<String>(newUser,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteuser/{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userid )
	{
		String userDel = userService.deleteUser(userid);
		return new ResponseEntity<String>(userDel,HttpStatus.OK);
	}
	
	@PutExchange("/changeusername/{userid}/{username}")
	public ResponseEntity<User> changeUserName(@PathVariable Long userid, @PathVariable String username )
	{
		User newUser = userService.updateUserName(userid, username);
		
		return new ResponseEntity<User>(newUser, HttpStatus.OK);
	}
	
}
