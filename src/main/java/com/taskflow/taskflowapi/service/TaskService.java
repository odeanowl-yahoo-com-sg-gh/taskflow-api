/**
 * Service interface for managing Task entities.
 * Provides standard CRUD operations and various filtering methods.
 * This interface defines the contract for Task-related operations, including creation, retrieval, updating, deletion, and various query methods based on task attributes.
 */
package com.taskflow.taskflowapi.service;

import com.taskflow.taskflowapi.entity.Task;
import java.util.List;
import java.time.LocalDate;

public interface TaskService {

  // Standard CRUD operations for Task entity
  Task createTask(Task task);
  List<Task> findAllTasks();
  Task findTaskById(Long id);
  Task updateTask(Long id, Task updatedTask);
  void deleteTask(Long id);
  Task saveTask(Task task);

  // Filtering operations for Task entity
  List<Task> findTasksByTitle(String title);
  List<Task> findTasksByDescription(String description);
  List<Task> findTasksByTitleAndDescription(String title, String description);
  List<Task> findTasksByTitleOrDescription(String title, String description);
  List<Task> findTasksByPriority(String priority);
  List<Task> findTasksByAssignee(String assignee);
  List<Task> findTasksByStatus(String status);
  List<Task> findTasksByCreatedDate(LocalDate createdDate);
  List<Task> findTasksByDueDate(LocalDate dueDate);
  List<Task> findTasksByCompletionStatus(boolean completed);
  List<Task> findTasksByTags(List<String> tags);
}
