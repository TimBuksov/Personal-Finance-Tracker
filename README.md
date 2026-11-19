# Personal Finance Tracker

A Java 21 / Spring Boot REST API with an accessible vanilla JavaScript dashboard for tracking income and expenses. **Portfolio demo: not financial advice.**

## Features
- Add, list, edit (API), and delete transactions
- Income / expense summaries and category totals; optional month filter
- Bean validation, JPA persistence (file-backed H2), responsive dashboard

## Run locally
Install **Java 21** and **Maven 3.9+**. From this directory:

```bash
mvn spring-boot:run
```

Open http://localhost:8080. Database files are created in `data/` and ignored by Git.

## API examples
```bash
curl -X POST http://localhost:8080/api/transactions -H 'Content-Type: application/json' -d '{"description":"Groceries","category":"Food","amount":42.50,"date":"2026-11-19","kind":"EXPENSE"}'
curl http://localhost:8080/api/transactions
curl 'http://localhost:8080/api/summary?month=2026-11'
```

## Tests
`mvn test`

## Next upgrades
User accounts, charts, export CSV, integration tests, production PostgreSQL.

## Security note
This is a **single-user local demo without authentication**. Do not deploy it publicly or use it for sensitive financial data.
