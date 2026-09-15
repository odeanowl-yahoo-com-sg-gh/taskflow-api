# Taskflow API - Custom Exception Handling (Step 1 Completed)

## Phase Overview
In this phase, we implemented centralized, custom exception handling to manage missing resource requests gracefully across the application. Generic runtime exceptions were replaced with a domain-specific `TaskNotFoundException`, and a global interceptor was configured to return standardized HTTP 404 responses.

---

## Key Changes & Implementations

### 1. Custom Exception (`TaskNotFoundException.java`)
* **Package**: `com.taskflow.taskflowapi.exception`
* Extends `RuntimeException` to provide an unchecked, domain-specific exception.
* Annotated with `@ResponseStatus(HttpStatus.NOT_FOUND)` to default to a `404` status code.
* Features overloaded constructors supporting both raw `Long id` parameters and custom message strings.

### 2. Service Layer Integration (`TaskService.java`)
* Refactored lookup, update, and deletion methods to leverage `TaskNotFoundException`:
  * **`findTaskById(Long id)`**: Uses `.orElseThrow(() -> new TaskNotFoundException(id))` on the repository `Optional`.
  * **`updateTask(Long id, Task task)`**: Reuses `findTaskById(id)` to validate existence before applying updates.
  * **`deleteTask(Long id)`**: Checks `existsById(id)` and throws `TaskNotFoundException` if the record is absent.

### 3. Global Exception Handler (`GlobalExceptionHandler.java`)
* **Package**: `com.taskflow.taskflowapi.exception`
* Annotated with `@ControllerAdvice` to intercept exceptions across all REST controllers.
* Uses `@ExceptionHandler(TaskNotFoundException.class)` to catch missing resource errors globally.
* Builds a clean, structured JSON response payload containing:
  * `timestamp`: Precise ISO execution timestamp (`LocalDateTime.now()`)
  * `status`: Numeric HTTP status code (`404`)
  * `error`: Error classification string (`"Task Not Found"`)
  * `message`: Detailed exception message (e.g., `"Task not found with id: 999"`)

---

## Postman Verification Guidelines

To verify the custom exception handling mechanism:

1. **Start the Application**:
   ```bash
   mvn spring-boot:run
