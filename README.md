# Employee and Department Management API
![Java](https://img.shields.io/badge/Java-17-blue) 
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-lightblue)

A Spring Boot REST API I built to manage company employees and their departments. It handles secure role-based access, data validation, and uses a Dockerized PostgreSQL database.

## ✨ What it does

- **Employee & Department Management**: Full CRUD operations with proper relationships
- **Role-Based Security**: Different access for EMPLOYEE, MANAGER, and ADMIN roles
- **Data Validation**: Ensures only valid data gets into the database
- **Containerized Database**: PostgreSQL running in Docker for easy setup
- **Auto Documentation**: Swagger UI for testing all endpoints
- **Global Error Handling**: Clean, consistent error messages
- **RESTful Best Practices**: Includes pagination and sorting for efficient data retrieval on large datasets.

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
- Docker & Docker Compose

### Option 1: Run locally (Spring Boot in IDE, DB in Docker)
1. **Clone the repository:**
   ```bash
   git clone https://github.com/Orvielle-Atanacio/Employee-Management-API.git
   cd Employee-Management-API
   ```
2. Start PostgreSQL in Docker:
    ```bash
    docker-compose up -d db
    ```
3. Load test data:
   ```bash
   docker cp ./src/main/resources/data.sql postgres:/tmp/data.sql

   docker exec -it postgres psql -U orvi27 -d employee_db -f /tmp/data.sql
   ```
   
5. Run the Spring Boot app locally:
   ```bash
   mvn spring-boot:run
   ```
   → Uses application.properties to connect to localhost:5332
   
### Option 2: Run everything in Docker (app + DB)
1. Build jar file:
   ```bash
   mvn clean package -DskipTests
   ```
2. Start containers:
   ```bash
   docker-compose up -d --build
   ```
3. Access the API:
   - **API**: http://localhost:8080
   - **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html)



## 🔐 Default Users

These users are automatically created when the application starts:

| Username | Password | Role | What they can do |
|----------|----------|------|------------------|
| employee | fun123 | EMPLOYEE | View data |
| manager  | fun123 | MANAGER | Create & update |
| admin    | fun123 | ADMIN | Full access + delete |

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
- Implementing efficient, scalable APIs using Spring Data's Pageable interface for pagination and sorting.

## 📸 API in Action

![Swagger UI Documentation](https://github.com/Orvielle-Atanacio/Employee-and-Department-Management-API/raw/main/url-based-roles-rest-security-practice/assets/swagger-ui.png)
