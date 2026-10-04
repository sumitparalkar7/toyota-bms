# Toyota Automated Logistics - Business Management System (Angular Frontend)

Angular standalone frontend for the BMS dashboard shown in the UI mockup.

## Stack
- Angular 22 standalone components
- TypeScript
- Reactive Forms
- Angular Router
- HttpClient / REST APIs
- CSS responsive enterprise UI
- Signals for local UI state

## Start

```bash
npm install
npm start
```

Open http://localhost:4200

## Spring Boot API contract

Set `src/environments.ts` if your backend URL is different:

```ts
apiBaseUrl: 'http://localhost:8080/api'
```

Expected endpoints:
- GET/POST/PUT/DELETE `/api/employees`
- GET/POST/PUT/DELETE `/api/fruits`
- GET/POST/PUT/DELETE `/api/garbage`
- GET `/api/dashboard/summary`

The screens include a small fallback sample dataset so the UI can be viewed even before the Spring Boot API is running.

## Logo

The sidebar currently uses a text placeholder mark. Replace it with the authorized Toyota Automated Logistics logo asset supplied by your organization, for example under `src/assets/`.

## Recommended next steps
1. Add Spring Boot + MySQL backend.
2. Add JWT login and role-based access.
3. Add real dashboard aggregation APIs.
4. Add Excel/PDF export.
5. Add audit logging and validation for one record per date.
