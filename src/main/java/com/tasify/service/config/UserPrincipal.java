package com.tasify.service.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tasify.entity.User;


public class UserPrincipal implements UserDetails 
{
	
	@Autowired
	private User user;
	
	public UserPrincipal(User user)
	{
		this.user=user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
		
		
	}

	@Override
	public String getPassword() 
	{
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() 
	{
		// TODO Auto-generated method stub
		return user.getUserName();
	}

}

