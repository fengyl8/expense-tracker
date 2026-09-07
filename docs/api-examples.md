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

## Filter Expenses

All valid list requests return HTTP 200, including requests with no matches.
Results depend on the current database contents. Omitting all filters lists all expenses.
Category matching is exact and case-sensitive. Dates use `YYYY-MM-DD` and both
boundaries are inclusive. Supplied category and date conditions must all match.

### Category Only

```http
GET http://localhost:8080/api/expenses?category=Food
```

Returns only expenses whose category is `Food` (HTTP 200).

### Date Range

```http
GET http://localhost:8080/api/expenses?from=2026-08-01&to=2026-08-31
```

Returns expenses dated August 1 through August 31, inclusive (HTTP 200).
To check a single day, use the same date for both boundaries:

```http
GET http://localhost:8080/api/expenses?from=2026-08-30&to=2026-08-30
```

### Category and Date Range

```http
GET http://localhost:8080/api/expenses?category=Food&from=2026-08-01&to=2026-08-31
```

Returns only `Food` expenses within the date range (HTTP 200).

### One Date Boundary

```http
GET http://localhost:8080/api/expenses?from=2026-08-30
GET http://localhost:8080/api/expenses?to=2026-08-30
```

The first request returns expenses on or after August 30; the second returns
expenses on or before August 30. Records without a date are excluded whenever
a date filter is supplied.

### Empty Filter Result

Assuming no expense has category `DoesNotExist`:

```http
GET http://localhost:8080/api/expenses?category=DoesNotExist
```

Response: HTTP 200, not 404.

```json
[]
```

An unmatched date range or combination also returns HTTP 200 with `[]`.
HTTP 404 is reserved for a missing expense ID.

### Invalid Date

```http
GET http://localhost:8080/api/expenses?from=not-a-date&to=2026-08-31
```

Response: HTTP 400.

```json
{"error":"from must be a valid date in YYYY-MM-DD format"}
```

An impossible calendar date also returns HTTP 400:

```http
GET http://localhost:8080/api/expenses?to=2026-02-30
```

```json
{"error":"to must be a valid date in YYYY-MM-DD format"}
```

### Reversed Date Range

```http
GET http://localhost:8080/api/expenses?from=2026-08-31&to=2026-08-01
```

Response: HTTP 400.

```json
{"error":"from must be on or before to"}
```

### Non-Numeric ID

```http
GET http://localhost:8080/api/expenses/abc
```

Response: HTTP 400.

```json
{"error":"id must be a number"}
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
