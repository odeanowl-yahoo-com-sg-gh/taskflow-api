package com.taskflow.taskflowapi.exception;

// Import statements for Java utility classes used in exception handling
import java.util.Map;
import java.time.LocalDateTime;
import java.util.HashMap;

// Global exception handler for the Taskflow API
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

// This class handles exceptions globally and provides appropriate HTTP responses.
@ControllerAdvice
public class GlobalExceptionHandler {

  // Handle TaskNotFoundException and return a structured error response
  @ExceptionHandler(TaskNotFoundException.class)
  // Method to handle TaskNotFoundException and return a structured error response
  public ResponseEntity<Map<String, Object>> handleTaskNotFoundException(TaskNotFoundException ex) {
      Map<String, Object> response = new HashMap<>();
      
      // Initialize the response map with the current timestamp
      response.put("timestamp", LocalDateTime.now());
      response.put("status", HttpStatus.NOT_FOUND.value());
      
      // Add the error message to the response map
      response.put("error", "Task not found");
      response.put("message", ex.getMessage());

      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  } // End of handleTaskNotFoundException method

} // End of GlobalExceptionHandler class
