/**
 * TaskService class for managing tasks.
 * Service layer for managing {@link com.taskflow.taskflowapi.entity.Task} entities.
 * This class provides task creation, update, deletion, and retrieval operations, including filtering by completion status, priority, due date, title, and description.
 */
package com.taskflow.taskflowapi.service;

/**
 * Import statements for required classes and annotations.
 */
import com.taskflow.taskflowapi.entity.Task;
import com.taskflow.taskflowapi.exception.TaskNotFoundException;
import com.taskflow.taskflowapi.repository.TaskRepository;

/**
 * Import statements for Lombok annotations and Spring framework classes.
 */
import lombok.RequiredArgsConstructor;

/**
 * Import statements for Spring stereotype and other required classes.
 */
import org.springframework.stereotype.Service;

/**
 * Import statements for List class, LocalDate class, and other required classes.
 */
import java.util.List;
import java.time.LocalDate;

/**
 * Service annotation to indicate that this class is a Spring service component
 */
@Service

/**
 * RequiredArgsConstructor annotation from Lombok to generate a constructor with required arguments.
 */
@RequiredArgsConstructor

/**
 * Service class for managing tasks.
 * Provides methods for creating, updating, deleting, and retrieving tasks.
 */
public class TaskServiceImpl implements TaskService {
    // Private field for the TaskRepository to perform CRUD operations on Task entities
    /**
     * Repository layer used for task CRUD operations.
     */
    private final TaskRepository taskRepository;
    
    /**
     * Creates a new task.
     *
     * @param task the task to create
     * @return the persisted task
     */
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    } // End of createTask method

    /**
     * Saves a task.
     *
     * @param task the task to save
     * @return the persisted task
     */
    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    } // End of saveTask method
    
    /**
     * Deletes a task by its identifier.
     *
     * @param id the identifier of the task to delete
     */
    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    } // End of deleteTask method by ID
    
    /**
     * Updates an existing task with the provided values.
     *
     * @param id the identifier of the task to update
     * @param updatedTask the task payload containing updated values
     * @return the updated task if found; otherwise {@code null}
     */
    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = findTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setAssignee(updatedTask.getAssignee());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setCreatedDate(updatedTask.getCreatedDate());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setCompleted(updatedTask.getCompleted());
        existingTask.setTags(updatedTask.getTags());
        
        return taskRepository.save(existingTask);
    } // End of updateTask method by ID

    // Method to find a task by its ID
    // TODO: Complete this method
    // Use taskRepository.findById(id) to retrieve the task by its ID
    // If not found, throw a RuntimeException with a message indicating that the task was not found: `new TaskNotFoundException("Task not found with id: " + id)` 
    /**
     * Finds a task by its identifier.
     *
     * @param id the identifier of the task to retrieve
     * @return the matching task
     * @throws TaskNotFoundException if no task exists for the given identifier
     */
    @Override
    public Task findTaskById(Long id) {
        // return taskRepository.findById(id)
        //         .orElse(null);
        // YOUR CODE HERE
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    } // End of findTaskById method by ID

    /**
     * Retrieves all tasks.
     *
     * @return a list containing all tasks
     */
    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    } // End of findAllTasks method
    
    /**
     * Finds tasks by completion status.
     *
     * @param completed the completion status to filter by
     * @return a list of tasks that match the provided completion status
     */
    @Override
    public List<Task> findTasksByCompletionStatus(boolean completed) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getCompleted() == completed)
                .toList();
    } // End of findTasksByCompletionStatus method by completion status

    /**
     * Finds tasks by priority.
     *
     * @param priority the priority to filter by (case-insensitive)
     * @return a list of tasks whose priority matches the provided value
     */
    @Override
    public List<Task> findTasksByPriority(String priority) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getPriority() != null && task.getPriority().equalsIgnoreCase(priority))
                .toList();
    } // End of findTasksByPriority method by priority

    /**
     * Finds tasks by status.
     *
     * @param status the status to filter by (case-insensitive)
     * @return a list of tasks whose status matches the provided value
     */
    @Override
    public List<Task> findTasksByStatus(String status) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus() != null && task.getStatus().equalsIgnoreCase(status))
                .toList();
    } // End of findTasksByStatus method by status

    /**
     * Finds tasks by assignee.
     *
     * @param assignee the assignee to filter by (case-insensitive)
     * @return a list of tasks whose assignee matches the provided value
     */
    @Override
    public List<Task> findTasksByAssignee(String assignee) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getAssignee() != null && task.getAssignee().equalsIgnoreCase(assignee))
                .toList();
    } // End of findTasksByAssignee method by assignee

    /**
     * Finds tasks by created date.
     *
     * @param createdDate the created date to filter by
     * @return a list of tasks whose created date equals the provided value
     */
    @Override
    public List<Task> findTasksByCreatedDate(LocalDate createdDate) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getCreatedDate() != null && task.getCreatedDate().isEqual(createdDate))
                .toList();
    } // End of findTasksByCreatedDate method by created date

    /**
     * Finds tasks by due date.
     *
     * @param dueDate the due date to filter by
     * @return a list of tasks whose due date equals the provided value
     */
    @Override
    public List<Task> findTasksByDueDate(LocalDate dueDate) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getDueDate() != null && task.getDueDate().isEqual(dueDate))
                .toList();
    } // End of findTasksByDueDate method by due date

    /**
     * Finds tasks by title.
     *
     * @param title the title to filter by (case-insensitive)
     * @return a list of tasks whose title matches the provided value
     */
    @Override
    public List<Task> findTasksByTitle(String title) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getTitle() != null && task.getTitle().equalsIgnoreCase(title))
                .toList();
    } // End of findTasksByTitle method by title

    /**
     * Finds tasks by description.
     *
     * @param description the description to filter by (case-insensitive)
     * @return a list of tasks whose description matches the provided value
     */
    @Override
    public List<Task> findTasksByDescription(String description) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getDescription() != null && task.getDescription().equalsIgnoreCase(description))
                .toList();
    } // End of findTasksByDescription method by description

    /**
     * Finds tasks containing all of the specified tags.
     *
     * @param tags the tags to match
     * @return tasks containing every specified tag
     */
    @Override
    public List<Task> findTasksByTags(List<String> tags) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getTags() != null && task.getTags().containsAll(tags))
                .toList();
    } // End of findTasksByTags method

    /**
     * Finds tasks that match both title and description.
     *
     * @param title the title to filter by (case-insensitive)
     * @param description the description to filter by (case-insensitive)
     * @return a list of tasks matching both title and description
     */
    @Override
    public List<Task> findTasksByTitleAndDescription(String title, String description) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getTitle() != null && task.getTitle().equalsIgnoreCase(title))
                .filter(task -> task.getDescription() != null && task.getDescription().equalsIgnoreCase(description))
                .toList();
    } // End of findTasksByTitleAndDescription method by both title and description

    /**
     * Finds tasks that match either title or description.
     *
     * @param title the title to filter by (case-insensitive)
     * @param description the description to filter by (case-insensitive)
     * @return a list of tasks matching either title or description
     */
    @Override
    public List<Task> findTasksByTitleOrDescription(String title, String description) {
        return taskRepository.findAll().stream()
                .filter(task -> (task.getTitle() != null && task.getTitle().equalsIgnoreCase(title)) ||
                        (task.getDescription() != null && task.getDescription().equalsIgnoreCase(description)))
                .toList();
    } // End of findTasksByTitleOrDescription method by either title or description
}// End of TaskService class
