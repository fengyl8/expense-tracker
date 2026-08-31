# API Examples

The application runs at `http://localhost:8080`.

## Health

```http
GET http://localhost:8080/api/health
```

Example response:

```json
{
  "status": "ok"
}
```

## Hello

```http
GET http://localhost:8080/api/hello?name=Fengyuan
```

Example response:

```json
{
  "message": "Hello, Fengyuan"
}
```

## Create an Expense

A successful request returns HTTP 201.

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

Example response:

```json
{
  "id": 1,
  "amount": 12.50,
  "description": "Lunch",
  "category": "Food",
  "date": "2026-08-17"
}
```

## List Expenses

```http
GET http://localhost:8080/api/expenses
```

Example response:

```json
[
  {
    "id": 1,
    "amount": 12.50,
    "description": "Lunch",
    "category": "Food",
    "date": "2026-08-17"
  }
]
```

## Get an Expense by ID

```http
GET http://localhost:8080/api/expenses/1
```

## Update an Expense

```http
PUT http://localhost:8080/api/expenses/1
Content-Type: application/json

{
  "amount": 20.00,
  "description": "Dinner",
  "category": "Food",
  "date": "2026-08-30"
}
```

## Delete an Expense

```http
DELETE http://localhost:8080/api/expenses/1
```

A successful deletion returns HTTP 204 with an empty body.

## Expense Not Found

```http
GET http://localhost:8080/api/expenses/999
```

Example response with HTTP 404:

```json
{
  "error": "Expense not found with id: 999"
}
```

## Invalid Amount

This request should return HTTP 400.

```http
POST http://localhost:8080/api/expenses
Content-Type: application/json

{
  "amount": 0,
  "description": "Lunch",
  "category": "Food",
  "date": "2026-08-17"
}
```

Example response:

```json
{
  "error": "Amount must be greater than 0"
}
```

## Blank Description

This request should return HTTP 400.

```http
POST http://localhost:8080/api/expenses
Content-Type: application/json

{
  "amount": 12.50,
  "description": "",
  "category": "Food",
  "date": "2026-08-17"
}
```

Example response:

```json
{
  "error": "Description must not be blank"
}
```
