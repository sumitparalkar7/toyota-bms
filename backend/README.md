# Toyota Automated Logistics BMS - Spring Boot Backend

## Versions
- Java 17
- Spring Boot 4.1.1
- Maven
- MySQL 8.x / MySQL Connector/J

## IntelliJ IDEA
Open `backend/pom.xml` or the `backend` folder as a Maven project. Select JDK 17.

## MySQL Workbench
Run:
```sql
CREATE DATABASE toyota_bms CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
Default local connection is `jdbc:mysql://localhost:3306/toyota_bms` with username `root`. Prefer setting `DB_USERNAME` and `DB_PASSWORD` as environment variables.

## Run
```bash
mvn clean spring-boot:run
```
API: `http://localhost:8080/api`
Health: `http://localhost:8080/actuator/health`

## Validation
Server-side Bean Validation rejects null values, future dates, negative employee counts/weights, and invalid decimal precision. Database constraints prevent duplicate records for the same date within each module.

## REST endpoints
- GET/POST `/api/employees`
- GET/PUT/DELETE `/api/employees/{id}`
- GET/POST `/api/fruits`
- GET/PUT/DELETE `/api/fruits/{id}`
- GET/POST `/api/garbage`
- GET/PUT/DELETE `/api/garbage/{id}`
- GET `/api/dashboard/summary`

## Deployment
`Procfile` and `system.properties` are included for Heroku-compatible deployment. Heroku free dynos are discontinued; use the paid Eco plan or another host. Set `PORT`, `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, and `FRONTEND_URL` as config/environment variables.
