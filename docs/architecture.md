# Architecture

## Modules

- `controller`: `ExpenseController` handles HTTP requests under `/api/expenses`, reads input, and calls the service.
- `service`: `ExpenseService` validates expenses, performs filtering, and handles missing IDs.
- `repository`: `ExpenseRepository` extends `JpaRepository<Expense, Long>` and provides database operations.
- `model`: `Expense` defines the JPA entity and its fields.
- `exception`: `GlobalExceptionHandler` returns consistent `{ "error": "..." }` responses for invalid input and missing expenses.
- `config`: `DataInitializer` inserts sample expenses when the table is empty.

## Request Flow

Client → Controller → Service → Repository → H2

For a filtered GET request, Spring converts `from` and `to` into `LocalDate` values. The controller passes the optional parameters to the service. The service checks the date range, calls `findAll()`, and filters the returned expenses before returning a JSON list.

Category matching is case-sensitive. Date boundaries are inclusive. Each supplied condition must match. Expenses without dates are excluded when a date filter is supplied.

An empty list returns HTTP 200 with `[]`. A missing expense ID returns 404. Invalid dates and reversed date ranges return 400.

## H2 Schema and Persistence

The `Expense` entity maps to the `expense` table using default JPA/Hibernate naming. Its columns are:

| Column | Java type | Purpose |
| --- | --- | --- |
| `id` | `Long` | Database-generated primary key using IDENTITY |
| `amount` | `double` | Expense amount |
| `description` | `String` | Expense description |
| `category` | `String` | Expense category |
| `date` | `LocalDate` | Expense date |

The database URL is `jdbc:h2:file:./data/expenses`. Data persists across restarts when the application uses the same working directory. Hibernate updates the schema through `ddl-auto=update`.

Sample data is inserted only when `count() == 0`, preventing duplicate seeding when existing rows are present. An empty table is seeded again on the next startup.

## Tradeoff: Filtering in the Service

Filtering after `findAll()` keeps the implementation simple and makes optional filters easy to combine. This is suitable for the current small portfolio project.

The cost is loading every expense into memory for each list request. For a larger dataset, I would move filtering into repository queries and add pagination so the database returns only the needed rows.
