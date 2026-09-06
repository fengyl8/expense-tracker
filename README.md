# Expense Tracker

My name is Fengyuan Liu.

This is an internship portfolio project for learning software engineering and building an expense tracking application.

## Technology Stack

- Java
- Spring Boot
- SQL

## Week 1

During Week 1, I set up my Java development environment, reviewed Spring Boot concepts, and created a plain Java expense program.

## How to Run the Week 1 Program

From the project root, compile the program with `javac -d out src/Expense.java src/Main.java`.
Then run it with `java -cp out Main`.
Alternatively, open the project in IntelliJ IDEA and run `Main.java`.

## Spring Boot Expense API

Week 2 introduced the create and list endpoints. Week 3 adds full CRUD operations and stores expenses in a file-based H2 database, so data remains after the application restarts.

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

The list endpoint supports an optional `category` query parameter,
for example `/api/expenses?category=Food`.
Category matching is exact and case-sensitive.
Omitting the parameter returns all expenses; no matches returns HTTP 200 with `[]`.

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

More request and response examples are available in [`docs/api-examples.md`](docs/api-examples.md).
The application request flow is explained in [`docs/request-flow.md`](docs/request-flow.md).
