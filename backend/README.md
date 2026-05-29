# 🍼 Baby Tracking API

A scalable, production-ready backend API designed to track and manage baby activities such as feeding, sleep, hygiene, and other daily routines.

This project is being developed in a collaborative environment following real-world backend engineering practices.

---

# 🚀 Overview

The Baby Tracking API provides authentication, user management, profile access, insights, and infrastructure for future baby activity tracking features.

The project focuses on:

* Clean Architecture
* Modular Design
* Scalability
* Team Collaboration
* Professional Backend Standards

---

# ⚙️ Tech Stack

* Node.js
* Express
* TypeScript
* PostgreSQL
* Prisma ORM
* JWT Authentication
* bcrypt
* Swagger / OpenAPI

---

# 🧠 Architecture

The project follows a modular architecture:

```txt
src/
├── config/
├── lib/
├── middlewares/
├── modules/
│   ├── auth/
│   ├── health/
│   ├── home/
│   ├── insights/
│   └── profile/
├── app.ts
└── server.ts
```

Each module contains:

* Controller
* Service
* Routes

---

# 🔐 Authentication

The API uses JWT authentication.

Protected routes require:

```http
Authorization: Bearer YOUR_TOKEN
```

---

# 🛠️ Installation

Clone repository:

```bash
git clone <repository-url>
```

Enter project folder:

```bash
cd backend
```

Install dependencies:

```bash
npm install
```

---

# 📄 Environment Variables

Create a `.env` file:

```env
DATABASE_URL=your_database_url
JWT_SECRET=your_jwt_secret
RESET_SECRET=your_reset_secret
PORT=3000
```

---

# 🗄️ Prisma Setup

Generate Prisma Client:

```bash
npx prisma generate
```

Run migrations:

```bash
npx prisma migrate dev
```

---

# ▶️ Run Project

Development:

```bash
npm run dev
```

Production:

```bash
npm run build
npm start
```

---

# 📚 API Documentation

Base URL:

```txt
http://localhost:3000/api
```

---

# 🏠 Home

### GET /api/home

Returns API status.

### Response

```json
{
  "message": "Welcome to BabyTracker API 🚀",
  "status": "running"
}
```

---

# 🩺 Health Check

### GET /api/health

Returns API health status.

### Response

```json
{
  "status": "ok",
  "message": "API is running"
}
```

---

# 🔐 Auth

## Register

### POST /api/auth/register

### Body

```json
{
  "name": "Vitor",
  "email": "vitor@email.com",
  "password": "123456"
}
```

### Response

```json
{
  "id": "uuid",
  "name": "Vitor",
  "email": "vitor@email.com"
}
```

---

## Login

### POST /api/auth/login

### Body

```json
{
  "email": "vitor@email.com",
  "password": "123456"
}
```

### Response

```json
{
  "token": "jwt_token",
  "user": {
    "id": "uuid",
    "name": "Vitor",
    "email": "vitor@email.com"
  }
}
```

---

## Forgot Password

### POST /api/auth/forgot-password

### Body

```json
{
  "email": "vitor@email.com"
}
```

### Response

```json
{
  "message": "If the email exists, a reset link has been sent",
  "resetToken": "reset_token"
}
```

---

## Reset Password

### POST /api/auth/reset-password

### Body

```json
{
  "token": "reset_token",
  "newPassword": "12345678"
}
```

### Response

```json
{
  "message": "Password updated successfully"
}
```

---

# 👤 Profile

### GET /api/profile

Protected route.

### Headers

```http
Authorization: Bearer jwt_token
```

### Response

```json
{
  "id": "uuid",
  "name": "Vitor",
  "email": "vitor@email.com"
}
```

---

# 📊 Insights

### GET /api/insights

Protected route.

### Headers

```http
Authorization: Bearer jwt_token
```

### Response

```json
{
  "totalActivities": 0,
  "activitiesByType": [],
  "lastActivity": null
}
```

---

# 🔑 Authentication Flow

1. Register a user

```http
POST /api/auth/register
```

2. Login

```http
POST /api/auth/login
```

3. Copy JWT token

4. Use token in protected routes

```http
Authorization: Bearer jwt_token
```

---

# 📄 Swagger

Swagger is configured and available locally:

```txt
http://localhost:3000/api/docs
```

---

# ✅ Features Implemented

* User Registration
* User Login
* JWT Authentication
* Password Recovery
* Password Reset
* Protected Routes
* Profile Endpoint
* Insights Endpoint
* Home Endpoint
* Health Check Endpoint
* Global Error Middleware
* Prisma Integration
* PostgreSQL Integration
* Swagger Setup

---

# 🚧 Next Features

* Baby CRUD
* Activity CRUD
* Social Login Improvements
* Refresh Tokens
* Email Integration
* Analytics Dashboard
* Automated Tests
* CI/CD Pipeline

---

# 👨‍💻 Team Project

This project is being developed collaboratively as part of a backend learning and simulation environment using professional development workflows.

---

# 📄 License

MIT License

