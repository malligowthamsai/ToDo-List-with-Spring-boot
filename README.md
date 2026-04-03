

// =======================
// README.md
// =======================

# 📝 To-Do List REST API (Spring Boot)

## 🚀 Features
- Create a task
- Get all tasks
- Get task by ID
- Update task
- Delete task

## 🛠 Tech Stack
- Java
- Spring Boot
- REST API

## ▶️ How to Run
1. Clone the repo
2. Open in IDE
3. Run `TodoApiApplication.java`
4. Server starts at: http://localhost:8080

## 📬 API Endpoints

### Get all todos
GET /api/todos

### Get by ID
GET /api/todos/{id}

### Create
POST /api/todos
Body:
{
  "title": "Learn Spring Boot",
  "completed": false
}

### Update
PUT /api/todos/{id}

### Delete
DELETE /api/todos/{id}

## 🧪 Testing with Postman
- Use POST, GET, PUT, DELETE methods
- Base URL: http://localhost:8080/api/todos

---

## 🌱 Future Scope
- Add database (MySQL/PostgreSQL)
- Add authentication (JWT)
- Add user-specific todos
- Add due dates & priority
- Build frontend (React/Angular)
- Deploy on cloud (AWS/Render)
