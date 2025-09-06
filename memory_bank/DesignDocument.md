# Personal Expense Manager – Design Document (SQLite Version)

An Android-native app for **tracking grocery expenses** via receipt upload and OCR, using a modern modular monolith stack with SQLite.

***

## Architecture Overview

The app comprises client (Android) and server (Spring Boot), communicating via a REST API. The server is structured in modules for each domain, still following the principles of modular monolith.

```
+----------------+
|   Android App  |
+--------+-------+
         |
        REST
         |
+--------+-------+
|   API Gateway  |         (Spring Boot 3 Modular Monolith, SQLite)
+--------+-------+
  |    |     |   |
User Receipt Store Item
  |    |     |   |
+------+------+---+
      SQLite
```
- **Backend**: Java 17, Spring Boot 3 (Modular Monolith)
- **Frontend**: Java (Android)
- **Database**: **SQLite** (embedded, file-based, lightweight)[4][5][1]
- **Module Examples**: User, Receipt, Store, Item

***

## Database Schema (for SQLite)

SQLite supports basic types: `INTEGER`, `REAL`, `TEXT`, and `BLOB`. The schema should be lean and adhere to SQLite’s constraints.

```sql
CREATE TABLE users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  clerk_id TEXT UNIQUE NOT NULL
);

CREATE TABLE stores (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  location TEXT
);

CREATE TABLE receipts (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  user_id INTEGER REFERENCES users(id),
  store_id INTEGER REFERENCES stores(id),
  date TEXT,
  total REAL,
  image_url TEXT
);

CREATE TABLE category (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);

CREATE TABLE productType (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT
);

CREATE TABLE items (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT,
  category INTEGER REFERENCES category(id),
  product_type INTEGER REFERENCES productType(id)
  
);

CREATE TABLE brand (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);

CREATE TABLE receipt_items (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  receipt_id INTEGER REFERENCES receipts(id),
  item_id INTEGER REFERENCES items(id),
  brand_name INTEGER REFERENCES brand(id)
  quantity INTEGER,
  weight FLOAT,
  amount REAL
);
```
- Foreign key constraints must be explicitly enabled for SQLite.
- All fields compatible with SQLite types.

***

## Stack

- **Backend**: Java 17, Spring Boot 3, Gradle, JPA/Hibernate (configure for SQLite dialect)
- **Frontend**: Java
- **Authentication**: Clerk
- **OCR**: Google Cloud Vision API
- **Deployment**: Docker, GitHub Actions

***

## SQLite-Specific Notes

- Embedded, serverless DB eliminates server configuration.[5]
- Suited for single-user/local deployments.[6][1]
- Less overhead: ideal for mobile and lightweight backend use.[5]
- Limited concurrent write access: app should implement transaction handling and avoid write spikes.

***

## Updated Section Summaries

### Backend Layers & Models

All domain models remain as Lombok-annotated POJOs, adapted for SQLite column types. Use integer auto-incrementing IDs. Mappings and relationships must be adjusted for SQLite’s constraints and best practices.

### API Design

No change; endpoints remain unchanged. Data returned/received adheres to SQLite’s schema.

### OCR Integration
Identical workflow: images uploaded by users, processed via Google Cloud Vision, results mapped and stored in SQLite.

### Frontend Responsibilities

No change; operates via REST endpoints, receives/store data from SQLite-powered backend.

### Docker & CI/CD

Docker container should mount the SQLite DB file as a volume for persistence. No server setup needed. GitHub Actions pipeline unchanged, just use SQLite-compatible tests and migrations.

### Test Strategies

Integration and E2E tests should seed the SQLite file and run against it. Unit tests should mock Hibernate/JPA layer as needed.

***

## Major Advantages & Caveats (SQLite)

- Fast on small/medium read/write scales, perfect for mobile and embedded use.[5]
- Database is a single file, simple to backup, migrate, or reset.
- Limited in advanced types and concurrent writes; not suitable for high-load or multi-user server scenarios.[4][5]
- No built-in fine-grained access control; protect SQLite file and restrict access at OS level.