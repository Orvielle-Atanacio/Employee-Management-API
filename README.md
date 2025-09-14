# Employee and Department Management API

A Spring Boot REST API I built to manage company employees and their departments. It handles secure role-based access, data validation, and uses a Dockerized PostgreSQL database.

## ✨ What it does

- **Employee & Department Management**: Full CRUD operations with proper relationships
- **Role-Based Security**: Different access for EMPLOYEE, MANAGER, and ADMIN roles
- **Data Validation**: Ensures only valid data gets into the database
- **Containerized Database**: PostgreSQL running in Docker for easy setup
- **Auto Documentation**: Swagger UI for testing all endpoints
- **Global Error Handling**: Clean, consistent error messages

## 🛠️ Built With

- **Java 17** + **Spring Boot 3**
- **Spring Security** with database authentication
- **Spring Data JPA** + **PostgreSQL**
- **Docker** & **Docker Compose**
- **Maven** for building
- **Swagger** for documentation

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven
- Docker

### Running the application

1. **Start the database:**
   ```bash
   docker-compose up -d
   ```
2. Run the Spring Boot app:

    ```bash
    mvn spring-boot:run
    ```
Access the API:

- API: http://localhost:8080

- Swagger UI: http://localhost:8080/swagger-ui.html

## 🔐 Default Users

These users are automatically created when the application starts:

| Username | Password | Role | What they can do |
|----------|----------|------|------------------|
| john | fun123 | EMPLOYEE | View data |
| mary  | fun123 | MANAGER | Create & update |
| susan    | fun123 | ADMIN | Full access + delete |

## 🗂️ Project Structure

**employee-management-api/**  
├── **src/main/**  
│   ├── **java/com/luv2code/springboot/cruddemo/**  
│   │   ├── `controller/`       → REST API endpoints  
│   │   ├── `entity/`           → JPA database models  
│   │   ├── `repository/`       → Data access layer  
│   │   ├── `service/`          → Business logic  
│   │   ├── `dto/`              → Request/response objects  
│   │   ├── `exception/`        → Error handling  
│   │   └── `security/`         → Authentication config  
│   │  
│   └── **resources/**  
│       ├── `application.properties` → App configuration  
│       ├── `data.sql`               → Test data  
│  
├── `docker-compose.yml`    → Database setup  
├── `pom.xml`               → Maven dependencies  
└── `README.md`             → Project documentation  

## 🧠 What I Learned

- Implementing Spring Security with role-based authorization
- Creating proper @ManyToOne relationships between entities  
- Global exception handling with @ControllerAdvice
- Containerizing databases with Docker
- Building REST APIs with proper validation
- Using DTOs to separate API schema from database entities
