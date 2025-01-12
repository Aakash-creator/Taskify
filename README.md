# Task Manager - Taskify

The Task Manager application is designed to help users efficiently manage and organize their tasks. It provides functionalities for creating, updating, and categorizing tasks, as well as managing tags and tracking completion status.

## About

This project aims to streamline task management for users by offering intuitive interfaces and robust backend services. It incorporates security features, token-based authentication, and efficient data handling to deliver a seamless task management experience. Designed using Spring Boot, it serves as a scalable and maintainable solution for individual or enterprise-level task tracking.

## Technologies Used

- **Spring Boot** for application development and RESTful APIs
- **Spring Data JPA** for database interactions
- **Spring Boot Starter Mail** for email notifications
- **Spring Security and OAuth2** for securing endpoints and authentication
- **Spring Boot Starter Validation** for input validation
- **Spring Boot Starter Web** for building web applications
- **SpringDoc OpenAPI** for generating API documentation
- **JSON Web Token (JWT)** for secure token-based authentication
- **MySQL** for persistent data storage
- **Spring Boot DevTools** for hot reloading during development
- **Tomcat** as the embedded servlet container
- **JUnit and Spring Security Test** for unit and integration testing

## Features

- **Task Management**: Users can create, update, delete, and retrieve tasks with details including name, description, due date, and completion status.
- **Tag Management within Tasks**: Tags are created and associated only within the context of tasks to maintain a structured tagging system.
- **Task Categories**: Tasks can be categorized to provide better organization and easier management.
- **User-Specific Tasks**: Tasks are linked to specific users to ensure personalized task management.
- **Task Notifications (Planned)**: Email notifications for task deadlines and updates.
- **Secure API Endpoints**: Implemented using Spring Security with OAuth2 and JWT for authentication and authorization.
- **Validation**: Input fields are validated to ensure data integrity and prevent errors.
- **API Documentation**: Comprehensive API documentation generated with SpringDoc OpenAPI.
- **Database Connectivity**: Utilizes MySQL for efficient data storage and retrieval.
- **Developer Tools**: Includes devtools for hot reload and rapid development.

## Getting Started

These instructions will help you set up the project on your local machine for development and testing purposes.

### Prerequisites

- Java 21 or higher
- Maven or Gradle
- MySQL

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/task-manager.git
   ```
2. Navigate to the project directory:
   ```bash
   cd task-manager
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Configure your database connection in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/taskify
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```
6. Access the application at `http://localhost:8080`.

## About the Creator

Created by Aakash Mane https://github.com/Aakash-creator/Taskify . Passionate about developing scalable backend solutions and robust APIs.

### Connect with Me

- GitHub: https://github.com/Aakash-creator
- LinkedIn: [linkedin.com/in/yourname](https://linkedin.com/in/yourname)
- Email: aakashmaneak@gmail.com

### License

- This project is licensed under the MIT License - see the LICENSE file for details.
