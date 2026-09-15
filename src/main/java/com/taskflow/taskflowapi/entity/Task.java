/**
 * Task entity representing a task in the task management system.
 * <p>This class is mapped to the {@code tasks} table and uses Lombok to generate
 * getters, setters, and constructors.</p>
*/

package com.taskflow.taskflowapi.entity;

// Import statements for JPA annotations and Lombok annotations
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
// Import statements for Lombok annotations
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
// Import statement for LocalDate class
import java.time.LocalDate;
// Import statement for List class
import java.util.List;

// JPA annotations to specify that this class is an entity and map it to the "tasks" table in the database
@Entity
// Lombok annotations to generate getters, setters, no-args constructor, and all-args constructor
// Class definition for the Task entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// Class definition for the Task entity
public class Task {
    /**
     * Primary key of the task.
    *
    * JPA annotations to specify the primary key and its generation strategy
    * <p>The value is generated automatically using identity strategy.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Task title.
     *
     * JPA annotation to specify that the title field is a column in the "tasks" table and it cannot be null
     * <p>Mapped as a non-null column in the {@code tasks} table.</p>
    */
    @Column(nullable = false)
    private String title;

    /**
     * Task description.
     *
     * JPA annotation to specify that the description field is a column in the "tasks" table
    */
    private String description;

    /**
     * Task priority.
     * 
     * JPA annotation to specify that the priority field is a column in the "tasks" table
    */
    private String priority;

    /**
     * Task assignee.
     * 
     * JPA annotation to specify that the assignee field is a column in the "tasks" table
    */
    private String assignee;

    /**
     * Task status.
     * 
     * JPA annotation to specify that the status field is a column in the "tasks" table
    */
    private String status;

    /**
     * Task created date.
     * 
     * JPA annotation to specify that the createdDate field is a column in the "tasks" table
    */
    private LocalDate createdDate;

    /**
     * Due date and time for task completion.
     * 
     * JPA annotation to specify that the dueDate field is a column in the "tasks" table
     */
    @Column
    private LocalDate dueDate;

    /**
     * Completion status of the task.
     * 
     * JPA annotation to specify that the completed field is a column in the "tasks" table
    */
    @Column(nullable = false)
    private boolean completed;

    /**
     * Create a separate join table 'task_tags' to store the many-to-many relationship between tasks and tags.
     * This join table will have two foreign keys: task_id and tag_id, referencing the primary keys of the tasks and tags tables, respectively.
     * The join table will allow a task to have multiple tags and a tag to be associated with multiple tasks.
     */
    @ElementCollection
    @CollectionTable(name = "task_tags", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "tag")
    private List<String> tags;

    /**
     * Constructor for creating a new Task with required fields.
     *
     * @param title       the title of the task
     * @param description the description of the task
     * @param priority    the priority of the task
     * @param assignee    the assignee of the task
     * @param status      the status of the task
     * @param createdDate the created date of the task
     * @param dueDate     the due date of the task
     * @param completed   the completion status of the task
     */
    public Task(String title, String description, String priority, String assignee, String status, LocalDate createdDate, LocalDate dueDate, boolean completed) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assignee = assignee;
        this.status = status;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.completed = completed;
        this.tags = List.of(); // Initialize tags as an empty list
    } // end of constructor
} // end of Task class