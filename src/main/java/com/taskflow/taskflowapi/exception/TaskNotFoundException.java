/**
 * Custom exception thrown when a task with a specified ID is not found.
 */
package com.taskflow.taskflowapi.exception;

// Import statements for Spring framework annotations and HTTP status codes.
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception for handling cases where a task is not found in the system.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends RuntimeException {
  /**
   * Constructs a new TaskNotFoundException with the specified task ID.
   *
   * @param id the ID of the task that was not found
   */
  public TaskNotFoundException(Long id) {
    super("Task not found with id: " + id);
  } // End of TaskNotFoundException(Long id) constructor
  
  /**
   * Constructs a new TaskNotFoundException with the specified message.
   *
   * @param message the detail message for the exception
   */
  public TaskNotFoundException(String message) {
    super(message);
  } // End of TaskNotFoundException(String message) constructor
} // End of TaskNotFoundException class