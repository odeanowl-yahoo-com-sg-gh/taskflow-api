# Taskflow API - Technical Debt & Future Scope Roadmap

## Overview
This document outlines deferred technical enhancements, architectural considerations, and future feature scopes identified during the Taskflow API refactoring process. While the core API adheres to clean MVC guidelines, exception handling, and service-interface decoupling, the following topics represent logical next steps for enterprise readiness.

---

## 1. Advanced Querying & Database Optimization

### Current State
* Custom query methods (e.g., filtering by status, priority, assignee, due date) are defined in `TaskService.java` and executed via Java Stream filtering on `findAll()` in `TaskServiceImpl.java`.

### Target Scope
* **Spring Data JPA Derived Queries**: Migrate in-memory Stream filters to `TaskRepository` interface methods (e.g., `findByStatusIgnoreCase(String status)`) to execute filtering directly at the database layer (H2/SQL).
* **Controller Search Endpoints**: Expose request parameters on `@GetMapping` endpoints in `TaskController.java` to allow dynamic REST queries (e.g., `GET /api/tasks?status=IN_PROGRESS&priority=HIGH`).

---

## 2. Data Transfer Object (DTO) Pattern & Mapping

### Current State
* The `Task` JPA entity is directly exposed across all controller endpoints (`@RequestBody` and `ResponseEntity<Task>`).

### Target Scope
* **DTO Separation**: Introduce `TaskRequestDTO` and `TaskResponseDTO` to encapsulate API view models and prevent over-posting or leaking database internal structure.
* **Automated Mapping**: Integrate **MapStruct** or **ModelMapper** to handle clean, zero-boilerplate conversions between entities and DTOs in the service layer.

---

## 3. Input Validation & Exception Handling

### Current State
* Entity fields lack explicit constraints, and request payloads rely on standard Java type checks.

### Target Scope
* **Bean Validation (`jakarta.validation`)**: Add constraints to entity/DTO fields (e.g., `@NotBlank`, `@Size`, `@FutureOrPresent`).
* **Validation Binding**: Apply `@Valid` on `@RequestBody` parameters in `TaskController.java`.
* **Global Error Handler Extension**: Add an `@ExceptionHandler(MethodArgumentNotValidException.class)` in `GlobalExceptionHandler.java` to format validation field errors into standard JSON error responses.

---

## 4. Database Seeding & Testing Strategy

### Current State
* H2 database relies on Spring Data JPA auto-ddl generation without initial dataset scripts.

### Target Scope
* **SQL Seeding**: Add `schema.sql` and `data.sql` scripts under `src/main/resources` to pre-populate mock task data for development and manual testing.
* **Automated Unit & Integration Testing**: Implement `@WebMvcTest` for `TaskController` and `@DataJpaTest` for `TaskRepository` to establish automated test coverage.