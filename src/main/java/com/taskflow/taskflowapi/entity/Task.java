/**
 * Represents a task entity in the task management system.
 * Contains fields for task attributes such as title, description, priority, assignee, status, created date, due date, completion status, and tags.
 * Mapped to the "tasks" table in the database.
 *
 * This class uses JPA annotations for ORM mapping and Lombok annotations for boilerplate code generation.
 */
package com.taskflow.taskflowapi.entity;

// Import statements for JPA annotations
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;

// Import statements for Lombok annotations to generate boilerplate code like getters, setters, and constructors
// Data annotation generates getters, setters, toString, equals, and hashCode methods for the class
import lombok.Data;
// NoArgsConstructor annotation generates a no-arguments constructor
import lombok.NoArgsConstructor;
// AllArgsConstructor annotation generates a constructor with all fields as arguments
import lombok.AllArgsConstructor;

// Import statement for LocalDate class
import java.time.LocalDate;
// Import statement for List class
import java.util.List;
import java.util.ArrayList;

// JPA annotations to specify that this class is an entity and map it to the "tasks" table in the database
@Entity

// Lombok annotations to generate getters, setters, no-args constructor, and all-args constructor
// Class definition for the Task entity
/**
 * JPA annotation to specify the table name for the Task entity.
 */
@Table(name = "tasks")

/**
 * Lombok annotation to generate getters, setters, toString, equals, and hashCode methods for the class.
 */
@Data

/**
 * Lombok annotation to generate a no-arguments constructor for the class.
 */
@NoArgsConstructor

/**
 * AllArgsConstructor annotation generates a constructor with all fields as arguments.
 */
@AllArgsConstructor

// Class definition for the Task entity
/**
 * Represents a task entity with attributes such as title, description, priority, assignee, status, created date, due date, completion status, and tags.
 */
public class Task {
    /**
     * Primary key of the task.
     */
    @Id
    /**
     * Specifies that the primary key value is automatically generated using the identity strategy.
     * 
     * JPA annotations to specify the primary key and its generation strategy
     * The value is generated automatically using identity strategy.     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * The unique identifier of the task.
     * Field is mapped to the primary key column in the "tasks" table.
     * Field is of type Long and represents the unique identifier for each task.
     */
    private Long id;

    /**
     * Task title.
     *
     * JPA annotation to specify that the title field is a column in the "tasks" table and it cannot be null
     * <p>Mapped as a non-null column in the {@code tasks} table.</p>
    */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Task description.
     *
     * JPA annotation to specify that the description field is a column in the "tasks" table
    */
    @Column(name = "description")
    private String description;

    /**
     * Task priority.
     * 
     * JPA annotation to specify that the priority field is a column in the "tasks" table
    */
    @Column(name = "priority")
    private String priority;

    /**
     * Task assignee.
     * 
     * JPA annotation to specify that the assignee field is a column in the "tasks" table
    */
    @Column(name = "assignee")
    private String assignee;

    /**
     * Task status.
     * 
     * JPA annotation to specify that the status field is a column in the "tasks" table
    */
    @Column(name = "status")
    private String status;

    /**
     * Task created date.
     * 
     * JPA annotation to specify that the createdDate field is a column in the "tasks" table
    */
    @Column(name = "created_date")
    private LocalDate createdDate;

    /**
     * Due date and time for task completion.
     * 
     * JPA annotation to specify that the dueDate field is a column in the "tasks" table
     */
    @Column(name = "due_date")
    private LocalDate dueDate;

    /**
     * Completion status of the task.
     * 
     * JPA annotation to specify that the completed field is a column in the "tasks" table
     */
    @Column(name = "completed", nullable = false)
    private Boolean completed;

    /**
     * Tags associated with the task.
     *
     * Create a separate join table 'task_tags' to store the many-to-many relationship between tasks and tags.
     * This join table will have two foreign keys: task_id and tag_id, referencing the primary keys of the tasks and tags tables, respectively.
     * The join table will allow a task to have multiple tags and a tag to be associated with multiple tasks.
     *
     * JPA annotation to specify that the tags field is an element collection and mapped to a separate table
     */
    @ElementCollection
    @CollectionTable(name = "task_tags", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();
} // end of Task class