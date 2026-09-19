/**
 * Controller class for handling task-related HTTP requests.
 */
package com.taskflow.taskflowapi.controller;

// Import statements for Task entity
import com.taskflow.taskflowapi.entity.Task;
// Import statement for TaskService class
import com.taskflow.taskflowapi.service.TaskService;

// Import statement for Lombok's RequiredArgsConstructor annotation
import lombok.RequiredArgsConstructor;

// Import statements for Spring annotations
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

// Import statement for List class
import java.util.List;

/**
 * REST controller for task management endpoints.
 * RestController annotation to indicate that this class is a Spring REST controller
 * RequestMapping annotation to specify the base URL for all endpoints in this controller
 */
@RestController

/**
 * Base URL mapping for all task-related endpoints.
 */
@RequestMapping("/api/tasks")

/**
 * RequiredArgsConstructor annotation to generate a constructor with required arguments.
 */
@RequiredArgsConstructor 

// Class definition for TaskController
/**
 * Controller class for managing task-related HTTP requests.
 */
public class TaskController {
    // Private final field for TaskService to handle business logic related to tasks
    /**
     * Service layer used for task operations.
     */
    private final TaskService taskService;   

    // Additional methods for handling HTTP requests (e.g., GET, POST, PUT, DELETE) will be added here
    // Method to handle GET requests for retrieving all tasks
    @GetMapping
    /**
     * Retrieves all tasks.
    *
    * @return HTTP 200 response containing all tasks
    */
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.findAllTasks();
        return ResponseEntity.ok(tasks);
    } // End of getAllTasks method

    @GetMapping("/{id}")
    /**
     * Retrieves a task by its identifier.
    *
    * @param id task identifier
    * @return HTTP 200 with the task when found, or HTTP 404 when not found
    */
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.findTaskById(id);
        // Since taskService.findTaskById throws an exception if the task is not found, we can rely on this behavior instead of manually checking for null.
        // the following code is left as is for consistency and clarity but commented out.
        // task is null will never happen due to the exception thrown by taskService.findTaskById.
            // if (task != null) {
            //     return ResponseEntity.ok(task);
            // } else {
            //     return ResponseEntity.notFound().build();
            // }
        return ResponseEntity.ok(task);
    } // End of getTaskById method
    // } // End of getTaskById method

    // Method to handle POST requests for creating a new task
    @PostMapping
    /**
     * Creates a new task.
     *
     * @param task task payload to create
     * @return HTTP 201 response containing the created task
     */
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        // return ResponseEntity.ok(createdTask);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    } // End of createTask method

    // Additional methods for handling other HTTP requests (e.g., PUT, DELETE) can be added here
    @PutMapping("/{id}")
    /**
     * Updates an existing task.
     *
     * @param id task identifier
     * @param task task payload with updated information
     * @return HTTP 200 response containing the updated task
     */
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    } // End of updateTask method

    @DeleteMapping("/{id}")
    /**
     * Deletes a task by its identifier.
     *
     * @param id task identifier
     * @return HTTP 204 response when the task is successfully deleted
     */
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    } // End of deleteTask method
} // End of TaskController class
