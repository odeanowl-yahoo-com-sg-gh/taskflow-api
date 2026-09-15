package com.taskflow.taskflowapi.exception;

// Import statements for Spring framework annotations and HTTP status codes.
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception for handling cases where a task is not found in the system.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {

  public TaskNotFoundException(Long id) {
    super("Task not found with id: " + id);
  } // End of TaskNotFoundException(Long id) constructor
  
  public TaskNotFoundException(String message) {
    super(message);
  } // End of TaskNotFoundException(String message) constructor
} // End of TaskNotFoundException class