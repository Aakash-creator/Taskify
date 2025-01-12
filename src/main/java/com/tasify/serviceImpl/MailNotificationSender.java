package com.tasify.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailNotificationSender 
{
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String fromMailId;

	
	
	public void sendmail(String recipient, String subject ,  String body)
	{
		SimpleMailMessage mailSender = new SimpleMailMessage();
		
		mailSender.setFrom(fromMailId);
		mailSender.setTo(recipient);
		mailSender.setText(body);
		mailSender.setSubject(subject);
		
		javaMailSender.send(mailSender);
		
		
	}
	
}
