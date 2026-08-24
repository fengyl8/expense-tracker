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

## Week 2 Spring Boot API

The Week 2 project provides a REST API for creating and listing expenses. Data is stored in memory and is cleared when the application stops.

### Requirements

- Java 21

### Run the Application

On Windows, run the application from the project root:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, open the project in IntelliJ IDEA and run `ExpenseTrackerApplication.java`.

The application runs at:

```text
http://localhost:8080
```

### Endpoints

- `GET /api/health` — check that the application is running
- `GET /api/hello?name=Fengyuan` — return a greeting
- `POST /api/expenses` — create an expense
- `GET /api/expenses` — list all expenses

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