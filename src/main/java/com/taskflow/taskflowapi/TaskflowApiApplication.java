// Main application class for the Taskflow API
package com.taskflow.taskflowapi;

// Import statements for Spring Boot annotations
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Boot annotation to specify that this is the main application class
@SpringBootApplication
/**
 * Main class for the Taskflow API Spring Boot application.
 */
public class TaskflowApiApplication {

	public static void main(String[] args) {
		// Start the Spring Boot application by invoking the run method of the SpringApplication class
		SpringApplication.run(TaskflowApiApplication.class, args);
	} // End of main method

} // End of TaskflowApiApplication class