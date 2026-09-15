// Controller class for handling task-related HTTP requests
package com.taskflow.taskflowapi.controller;

// Import statements for Task entity
import com.taskflow.taskflowapi.entity.Task;
// Import statement for TaskService class
import com.taskflow.taskflowapi.service.TaskService;

// Import statements for Spring annotations
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/tasks")

// Class definition for TaskController
public class TaskController {
    @Autowired
    // Private final field for TaskService to handle business logic related to tasks
    private TaskService taskService;

    /**
     * Creates a controller with the required task service.
     * Constructor for TaskController that takes a TaskService as a parameter
     *
     * @param taskService service layer used for task operations
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    } // End of TaskController constructor

    // Method to handle GET requests for retrieving all tasks
    @GetMapping
    // Additional methods for handling HTTP requests (e.g., GET, POST, PUT, DELETE) will be added here
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
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    } // End of getTaskById method

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
}
