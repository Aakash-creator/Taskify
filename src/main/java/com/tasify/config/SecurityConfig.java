package com.tasify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig 
{
	
	@Autowired
	private UserDetailsService UserDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
//		disable csrftoken
		http.csrf(custom ->custom.disable() );
		
		http
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeHttpRequests(
					auth ->  auth.requestMatchers(
							new AntPathRequestMatcher("/**/**/**"),
							// here i am letting all the endpoint to get through without checking. If you want you can only 
							// pass few endpoints and secure the application  
							new AntPathRequestMatcher("/auth/**")
//							new AntPathRequestMatcher("/createtask")
					
							
					).permitAll()
					.anyRequest().authenticated()); // remainng  auth2Login(Customizer.withDefaults());
//			.httpBasic(Customizer.withDefaults());
			
		
//	http.logout(log -> log.logoutUrl("/logoutuser"));
			
		return http.build();
		
	}

	@Bean
	public AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(UserDetailsService);
		daoProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return daoProvider;
		
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() 
	{
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config)  throws Exception
	{
		
		return config.getAuthenticationManager();
		
	}
	
	
}
