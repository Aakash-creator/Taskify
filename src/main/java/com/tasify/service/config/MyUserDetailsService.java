package com.tasify.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tasify.Repo.IUserRepo;
import com.tasify.entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User Not Found"); 
		}
		return new UserPrincipal(user);
	}

}
