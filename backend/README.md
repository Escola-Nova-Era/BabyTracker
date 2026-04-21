# 🍼 Baby Tracking API

A scalable, production-ready backend API designed to track and manage baby activities such as feeding, sleep, and hygiene.

This is an **open-source educational project**, developed as part of a collaborative learning environment, simulating real-world backend development.

Built following real-world backend architecture principles, this project focuses on **scalability**, **clean code**, and **team collaboration**.

---

## 🚀 Overview

The **Baby Tracking API** is part of a collaborative project developed by a team of developers.

It allows parents and caregivers to track essential baby routines while providing developers with a **hands-on experience in building production-like backend systems**.

This project simulates a real-world environment where developers:
- Work with structured tasks
- Collaborate through Git workflows
- Build scalable APIs
- Follow professional coding standards

---

## ⚡ Quick Start

```bash
git clone <your-repo-url>
cd backend
npm install
cp .env.example .env
npx prisma migrate dev
npm run dev
🧠 Architecture

This project follows a modular and layered architecture:

Controller Layer → Handles HTTP requests and responses
Service Layer → Contains business logic
Database Layer (Prisma) → Handles data access
📁 Folder Structure
src/
├── config/        # Environment configuration
├── lib/           # Prisma client instance
├── modules/       # Feature-based modules
│   ├── auth/
│   ├── user/
│   ├── activity/
│   └── health/
├── middlewares/   # Global middlewares
├── app.ts         # Express app setup
└── server.ts      # Server entry point
⚙️ Tech Stack
Node.js
Express
TypeScript
PostgreSQL (Neon)
Prisma ORM
JWT Authentication
bcrypt (password hashing)
🗄️ Database Design

The database is designed for scalability and flexibility.

Relationships
A User can have multiple Babies
A Baby can have multiple Activities
Models
User
Baby
Activity
Activity Types
FEEDING
SLEEP
HYGIENE
🔐 Authentication

The API includes a complete authentication system:

User registration
Login with email and password
Social login
JWT-based authentication
Protected routes using middleware
📌 Endpoints
POST /auth/register
POST /auth/login
POST /auth/social
🔑 Authorization

Protected routes require a valid JWT token:

Authorization: Bearer <token>
🔑 Login Example

The login endpoint validates user credentials using bcrypt and returns a JWT token.

Request
POST /auth/login
Response
{
  "user": {
    "id": "uuid",
    "email": "user@email.com"
  },
  "token": "jwt_token_here"
}
📡 API
Base URL
http://localhost:3000/api
Health Check
GET /health
Response
{
  "status": "ok",
  "message": "API is running"
}
Register User
POST /auth/register
Body
{
  "name": "Vitor",
  "email": "vitor@email.com",
  "password": "123456"
}
Response
{
  "id": "uuid",
  "name": "Vitor",
  "email": "vitor@email.com"
}
🛠️ Setup
1. Install dependencies
npm install
2. Environment variables

Create a .env file based on .env.example:

PORT=3000
DATABASE_URL=your_database_url
JWT_SECRET=your_secret_key
3. Run migrations
npx prisma migrate dev --name init
4. Generate Prisma Client
npx prisma generate
5. Start the server
npm run dev
🧪 Testing

You can test the API using:

Postman
Insomnia
Thunder Client (VS Code)
📈 Roadmap
✅ User registration
✅ Login with JWT authentication
🔄 Social authentication improvements
🔄 Refresh tokens
👶 Baby management endpoints
📝 Activity tracking endpoints
📊 Analytics (sleep patterns, feeding frequency)
📄 Swagger API documentation
🧪 Automated tests (Jest)
🤝 Contributing

This is an open-source educational project, and contributions are welcome.

Fork the repository
Create a new branch (feature/your-feature)
Commit your changes
Open a Pull Request
🧠 Key Learning Points

This project demonstrates:

Clean backend architecture
Modular design
Real-world API structure
Database modeling with Prisma
Secure authentication practices
Team collaboration using Git workflows
📄 License

This project is open-source and available under the MIT License.
