package com.tasify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "TaskBookingAPI",
				version = "v1.0",
				description = "This API is for Internal use and testing purpose"
				),
		servers = @Server(
				url="http://localhost:8089/TaskManagerAPI/",
				description = "deployed at 8089"
				)
		)
public class TaskManagerApplication 
{

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
