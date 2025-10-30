# Slab 2 Backend - Spring Boot Projects

This repository contains two independent Spring Boot Java backend projects for the CodexIntern Slab 2 assignment:

- **job-portal-management**: Job Portal Management System
- **event-management-system**: Event Management System

Each project is self-contained, uses in-memory H2 as database, and is ready to run.

---

## How to Run

1. Open a terminal in the respective project folder.
2. Run:
   ```
   mvn spring-boot:run
   ```
3. Once started, the backend is available at `http://localhost:8080` by default.

The H2 database console is at `http://localhost:8080/h2-console` (JDBC URL is auto-configured in each project).

---

## Project Overviews

### 1. Job Portal Management System
- Manage job postings and applications via REST API.
- Features:
  - CRUD for jobs
  - Search by title/company
  - Users can apply for jobs, list all applications, delete an application
- Main REST Endpoints:
    - `/api/jobs` CRUD for jobs
    - `/api/jobs/search?q=...` search jobs
    - `/api/applications` list/apply
    - `/api/applications/byjob/{jobId}` see all applications for a job

### 2. Event Management System
- Manage events and registrations via REST API.
- Simple authentication for non-GET actions (default Spring users).
- Features:
  - CRUD for events (title, desc, date, time, location, capacity)
  - Search/filter events by title, date, location
  - Register/cancel registration for an event (with capacity check)
  - List registrations by event/id/user
- Main REST Endpoints:
    - `/api/events` CRUD for events
    - `/api/events/search/`*`{title|location|date}`*`?q=...` search events
    - `/api/registrations` registration actions
    - `/api/registrations/byevent/{eventId}` view by event
    - `/api/registrations/byuser/{username}` view by user

---

> Customize or extend functionality as desired for your assignment submission!
