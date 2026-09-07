# Expense Tracker

My name is Fengyuan Liu.

Expense Tracker is an internship portfolio project built as a REST API. It supports creating, listing, updating, and deleting expenses, with optional category and date-range filters. Expenses are stored in a file-based H2 database and persist across application restarts when run from the same project directory.

## Technology Stack

- Java 21
- Spring Boot
- Spring Data JPA
- H2 SQL database (file-based)
- Maven Wrapper

## Spring Boot Expense API

Week 2 introduced the create and list endpoints. Week 3 added full CRUD and persistent storage. Week 4 adds category and date-range filtering on the existing list endpoint.

### Requirements

- Java 21

### Run the Application

On Windows, run the application from the project root:

```powershell
.\mvnw.cmd spring-boot:run
```

On Unix or macOS, run:

```bash
./mvnw spring-boot:run
```

Alternatively, open the project in IntelliJ IDEA and run `ExpenseTrackerApplication.java`.

The application runs at:

```text
http://localhost:8080
```

### Endpoints

| Method | Path | Description | Success status |
| --- | --- | --- | --- |
| GET | `/api/health` | Check application health | 200 |
| GET | `/api/hello?name=Fengyuan` | Return a greeting | 200 |
| POST | `/api/expenses` | Create an expense | 201 |
| GET | `/api/expenses` | List all expenses | 200 |
| GET | `/api/expenses/{id}` | Get an expense by ID | 200 |
| PUT | `/api/expenses/{id}` | Update an expense | 200 |
| DELETE | `/api/expenses/{id}` | Delete an expense | 204 |

GET, PUT, and DELETE requests for a non-existent expense ID return
404 with `{ "error": "Expense not found with id: <id>" }`.

### Filter Expenses

The list endpoint accepts optional `category`, `from`, and `to` query parameters.
Category matching is exact and case-sensitive. Dates use `YYYY-MM-DD` format,
and the range includes both start and end dates. Providing only `from` applies
a lower bound; providing only `to` applies an upper bound.
Omitting all parameters returns all expenses; no matches returns HTTP 200 with `[]`.
Expenses without a date are excluded when a date filter is provided.
Invalid dates or a `from` date later than `to` return HTTP 400 with `{ "error": "..." }`.

```http
GET http://localhost:8080/api/expenses?category=Food
GET http://localhost:8080/api/expenses?from=2026-08-01&to=2026-08-31
GET http://localhost:8080/api/expenses?category=Food&from=2026-08-01&to=2026-08-31
GET http://localhost:8080/api/expenses?category=DoesNotExist
GET http://localhost:8080/api/expenses?from=not-a-date&to=2026-08-31
```

### Example Create Request

```http
POST http://localhost:8080/api/expenses
Content-Type: application/json

{
  "amount": 12.50,
  "description": "Lunch",
  "category": "Food",
  "date": "2026-08-17"
}
```

## Documentation

- [API examples](docs/api-examples.md): example requests and responses.
- [Architecture](docs/architecture.md): modules, request flow, database schema, and the filtering tradeoff.
- [Request flow](docs/request-flow.md): how requests pass through the application layers.
- [Tutorial notes](docs/tutorial-notes.md): learning notes.

## Week 1 Program

During Week 1, I set up my Java development environment, reviewed Spring Boot concepts, and created a plain Java expense program.

From the project root, compile and run the program:

```text
javac -d out src/Expense.java src/Main.java
java -cp out Main
```

Alternatively, open the project in IntelliJ IDEA and run `Main.java`.
