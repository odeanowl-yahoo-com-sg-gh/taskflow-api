# Taskflow API - Project Status & Documentation

## Project Overview
Taskflow API is a Spring Boot 3 RESTful web service for managing tasks, project metadata, and tags using Spring Data JPA with an in-memory H2 database.

---

## 1. Current Implementation Status

### Core Architecture & Layers
* **Entity (`Task.java`)**: Configured with JPA `@Entity`, `@Id`, `@GeneratedValue(strategy = GenerationType.IDENTITY)`, `@ElementCollection` for task tags, and ISO date formatting (`LocalDate`).
* **Repository (`TaskRepository.java`)**: Extends `JpaRepository<Task, Long>`, supplying standard CRUD operations out of the box.
* **Service (`TaskService.java`)**: Encapsulates business logic for retrieving all tasks, finding tasks by ID, and persisting new tasks.
* **Controller (`TaskController.java`)**: Exposes `/api/tasks` endpoints (`GET /api/tasks`, `GET /api/tasks/{id}`, `POST /api/tasks`).
* **Application Config (`application.properties`)**: Standardized for H2 in-memory mode (`jdbc:h2:mem:taskflowdb`), auto-executing initialization scripts via `spring.sql.init.mode=always` and `spring.jpa.defer-datasource-initialization=true`.

---

## 2. Verification & Testing Evidence

The base requirements have been verified end-to-end:
1. **Application Startup**: Clean execution via `mvn spring-boot:run` running on port `8080`.
2. **Database Seeding**: Verified database initialization with 6 baseline tasks and tags from `initialTasks.sql`. Primary key sequence properly reset (`RESTART WITH 7`) to prevent key collisions.
3. **API Endpoint Functionality**:
   * **`GET /api/tasks`**: Returns HTTP 200 OK with the full array of tasks.
   * **`GET /api/tasks/1`**: Returns HTTP 200 OK with details for task 1.
   * **`POST /api/tasks`**: Successfully processed with Postman, returning HTTP 201 Created and persisting new entries (e.g., ID 7 and ID 8).
4. **H2 Database Console Verification**: Connected via `jdbc:h2:mem:taskflowdb` with username `sa`. SQL queries (`SELECT * FROM TASKS;`) confirm that newly posted tasks are saved directly into memory.

---

## 3. Master Roadmap & Remaining Tasks

### Step 1: Custom Exception Handling
* [ ] Create `TaskNotFoundException.java` extending `RuntimeException`.
* [ ] Refactor `TaskService.java` to throw `TaskNotFoundException` on missing records.
* [ ] Implement `@ControllerAdvice` (`GlobalExceptionHandler.java`) to map missing resource exceptions to structured `404 NOT FOUND` JSON responses.
* [ ] Verify exception behavior via Postman (`GET /api/tasks/999`).

### Step 2: Lombok Optimization
* [ ] **`Task.java`**: Clean up constructors and initialize `tags = new ArrayList<>()`.
* [ ] **`TaskService.java`**: Apply `@RequiredArgsConstructor` and `private final` fields to leverage clean constructor injection without `@Autowired`.
* [ ] **`TaskController.java`**: Apply `@RequiredArgsConstructor` and `private final` fields.

### Step 3: Full CRUD Completion
* [ ] Implement `@PutMapping("/{id}")` in `TaskController.java` to handle full task updates.
* [ ] Implement `@DeleteMapping("/{id}")` in `TaskController.java` returning `204 No Content`.

### Step 4: Architectural Design (Coding to an Interface)
* [ ] Extract `TaskService` into a Java interface.
* [ ] Move business implementation logic into `TaskServiceImpl.java` (`@Service`).
* [ ] Inject the `TaskService` interface directly into `TaskController.java` to achieve full decoupling.