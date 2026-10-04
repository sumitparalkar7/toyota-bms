# Toyota Automated Logistics BMS - Full Stack

Includes the supplied Angular frontend and a Java 17 Spring Boot backend.

## Structure
- `backend/` - open this folder in IntelliJ IDEA
- `frontend/` - Angular frontend using HttpClient

## Run
1. MySQL Workbench: `CREATE DATABASE toyota_bms CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;`
2. Backend: JDK 17, open `backend`, run `ToyotaBmsApplication`.
3. Frontend: open `frontend`, run `npm install` and `npm start`.
4. Open `http://localhost:4200`.

The frontend already calls `/api/...`; the included Angular proxy forwards those requests to Spring Boot during development.
