# Deployment configuration

## Heroku
Heroku no longer provides a free dyno tier. The included configuration is still compatible with Heroku:

- `Procfile`: starts the packaged Spring Boot JAR using Heroku's `$PORT`.
- `system.properties`: requests Java 17.

Build locally:
```bash
mvn clean package
```
Deploy the backend directory as a Heroku app. Set these config vars:
```text
DB_URL=jdbc:mysql://YOUR-MYSQL-HOST:3306/toyota_bms?useSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USERNAME=YOUR_DB_USER
DB_PASSWORD=YOUR_DB_PASSWORD
FRONTEND_URL=https://YOUR-ANGULAR-HOST
```
Do not put real database passwords into source control.

## Frontend production URL
Change `frontend/src/environments.ts` for production to your deployed API URL, for example:
```ts
export const environment = {
  production: true,
  apiBaseUrl: 'https://YOUR-BACKEND-HOST/api'
};
```
The local development proxy is in `frontend/proxy.conf.json` and forwards `/api` to `http://localhost:8080`.
