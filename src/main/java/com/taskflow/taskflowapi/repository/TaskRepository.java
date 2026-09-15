// Repository interface for Task entity, extending JpaRepository to provide CRUD operations
package com.taskflow.taskflowapi.repository;

// Import statements for Task entity
import com.taskflow.taskflowapi.entity.Task;

// Import statement for JpaRepository interface
import org.springframework.data.jpa.repository.JpaRepository;
// Import statement for Repository annotation
import org.springframework.stereotype.Repository;

// Repository annotation to indicate that this interface is a Spring Data repository
@Repository
// Interface definition for TaskRepository, extending JpaRepository to provide CRUD operations for Task entities
public interface TaskRepository extends JpaRepository<Task, Long> {
} // end of TaskRepository interface