# Request Flow

The Expense Tracker uses this request flow:

```text
Client -> Controller -> Service -> Repository -> H2 Database
```

## Controller

`ExpenseController` receives HTTP requests under `/api/expenses`. It reads request bodies and path variables, then calls `ExpenseService`.

## Service

`ExpenseService` validates expense data and chooses the repository operation. It also throws `ExpenseNotFoundException` when an ID does not exist.

## Repository

`ExpenseRepository` extends `JpaRepository<Expense, Long>`. It provides `save()`, `findAll()`, `findById()`, and `delete()` without requiring SQL code in the service.

## Database

The repository stores expenses in the file-based H2 database at `jdbc:h2:file:./data/expenses`. This allows expenses to remain after the application restarts.

`DataInitializer` adds three sample expenses only when the database is empty, so restarting the application does not create duplicates.

## Example

For `GET /api/expenses/1`, the controller passes ID `1` to the service. The service asks the repository to query H2. If the expense exists, it is returned to the client. Otherwise, the API returns HTTP `404` with a JSON error.
