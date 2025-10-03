# Task Management System

A full-stack **Task Management Application** built with **React (frontend)**, **Spring Boot (backend)**, and **PostgreSQL (database)**. This system helps users create, update, assign, and track tasks efficiently with a clean and responsive interface.

---

## 🚀 Features

- User registration & login with JWT authentication
- Create, edit, delete, and assign tasks
- Track task status: Pending, In Progress, Completed
- Responsive dashboard built with React
- RESTful APIs using Spring Boot
- PostgreSQL database for reliable data storage

---

## 🛠️ Tech Stack

- **Frontend:** React, Axios, Tailwind CSS/Bootstrap
- **Backend:** Spring Boot, Spring Security, REST API
- **Database:** PostgreSQL
- **Other Tools:** Maven, JWT, Docker (optional)

---

## 📦 Installation

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/task-management-system.git
cd task-management-system
```

### 2. Backend Setup (Spring Boot)

- Navigate to the `backend` folder
- Configure `application.properties` with your PostgreSQL database:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

- Run the Spring Boot application:

```bash
mvn spring-boot:run
```

### 3. Frontend Setup (React)

- Navigate to the `frontend` folder
- Install dependencies:

```bash
npm install
```

- Run the app:

```bash
npm start
```

The frontend will run on [http://localhost:3000](http://localhost:3000) and backend on [http://localhost:8080](http://localhost:8080).

---

## 📸 Screenshots

<!-- TODO -->

_(Add screenshots of your app UI here, e.g., dashboard, login page, task list)_

---

## 📖 API Endpoints (Sample)

| Method | Endpoint             | Description       |
| ------ | -------------------- | ----------------- |
| POST   | `/api/auth/register` | Register new user |
| POST   | `/api/auth/login`    | User login (JWT)  |
| GET    | `/api/tasks`         | Get all tasks     |
| POST   | `/api/tasks`         | Create new task   |
| PUT    | `/api/tasks/{id}`    | Update task by ID |
| DELETE | `/api/tasks/{id}`    | Delete task by ID |

---

## 🙌 Contributing

Contributions are welcome! Feel free to fork this repo and submit pull requests.

---

## 📄 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---
