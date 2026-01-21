# Learning Management System (LMS)

A secure RESTful API for managing students and users in a learning management system, built with Spring Boot 4.0.1 and secured with JWT authentication.

## 📋 Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Security](#security)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)

## ✨ Features

- **User Management**
  - User registration and authentication
  - Role-based access control (ADMIN)
  - Password encryption using BCrypt
  - JWT-based authentication

- **Student Management**
  - Add new students
  - Retrieve all students
  - Delete students by ID

- **Security**
  - JWT token-based authentication
  - Stateless session management
  - Role-based authorization
  - Secure password storage

- **API Documentation**
  - Interactive Swagger UI
  - OpenAPI 3.0 specification
  - Bearer token authentication in Swagger

## 🛠️ Technologies

### Core Framework
- **Spring Boot**: 4.0.1
- **Java**: 21

### Dependencies
- **Spring Boot Starter Web**: RESTful API development
- **Spring Boot Starter Data JPA**: Database persistence
- **Spring Boot Starter Security**: Authentication and authorization
- **MySQL Connector**: 9.3.0
- **Lombok**: 1.18.42 - Reduces boilerplate code
- **MapStruct**: 1.5.5.Final - DTO-Entity mapping
- **JJWT**: 0.11.5 - JWT token generation and validation
- **SpringDoc OpenAPI**: 2.8.13 - API documentation

### Database
- **MySQL**: Cloud-hosted database (Aiven Cloud)

## 🏗️ Architecture

The application follows a layered architecture pattern:

```
├── Controller Layer    → Handles HTTP requests
├── Service Layer       → Business logic implementation
├── Repository Layer    → Data access operations
├── Entity Layer        → Database models
├── DTO Layer          → Data transfer objects
├── Mapper Layer       → Entity-DTO conversions
├── Security Layer     → Authentication & authorization
└── Configuration      → Application configurations
```

## 📋 Prerequisites

- Java Development Kit (JDK) 21 or higher
- Maven 3.6+ 
- MySQL Database (or access to the configured cloud database)
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd learning-management-system
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   Or run the main class directly:
   ```bash
   java -jar target/learning-management-system-1.0-SNAPSHOT.jar
   ```

## ⚙️ Configuration

### Database Configuration

Update the `application-local.yml` file with your database credentials:

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://<host>:<port>/<database-name>?createDatabaseIfNotExist=true&useSSL=false
    username: <your-username>
    password: <your-password>
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### JWT Configuration

The JWT secret key is configured in `JwtUtil.java`. For production, move this to environment variables:

```java
private String secret = "your-secret-key";
```

Token expiration is set to 10 hours by default.

### Application Profiles

The application uses Spring profiles. Active profile is set in `application.yml`:

```yaml
spring:
  profiles:
    active: local
```

## 📚 API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

The Swagger UI includes JWT bearer token authentication support.

## 🔒 Security

### Authentication Flow

1. **User Registration**: POST to `/users/register` (requires ADMIN role)
2. **User Login**: POST to `/users/login` - Returns JWT token
3. **Authenticated Requests**: Include JWT token in Authorization header
   ```
   Authorization: Bearer <your-jwt-token>
   ```

### Security Configuration

- **Password Encoding**: BCrypt with default strength
- **Session Management**: Stateless (JWT-based)
- **Public Endpoints**: 
  - Swagger documentation (`/v3/api-docs/**`, `/swagger-ui/**`)
- **Protected Endpoints**: 
  - Student management (requires authentication)
  - User operations (requires ADMIN role)

### JWT Filter

The `JwtFilter` validates JWT tokens on each request:
- Extracts username from token
- Validates token expiration
- Sets authentication in SecurityContext

## 📁 Project Structure

```
learning-management-system/
├── src/
│   ├── main/
│   │   ├── java/edu/icet/lms/
│   │   │   ├── Main.java                    # Application entry point
│   │   │   ├── configuration/
│   │   │   │   └── OpenApiConfig.java       # Swagger/OpenAPI config
│   │   │   ├── controller/
│   │   │   │   ├── StudentController.java   # Student REST endpoints
│   │   │   │   └── UserController.java      # User REST endpoints
│   │   │   ├── dto/
│   │   │   │   ├── StudentDto.java          # Student data transfer object
│   │   │   │   └── UserDto.java             # User data transfer object
│   │   │   ├── entity/
│   │   │   │   ├── Student.java             # Student entity
│   │   │   │   └── User.java                # User entity
│   │   │   ├── mapper/
│   │   │   │   ├── StudentMapper.java       # Student DTO-Entity mapper
│   │   │   │   └── UserMapper.java          # User DTO-Entity mapper
│   │   │   ├── repository/
│   │   │   │   ├── StudentRepository.java   # Student data access
│   │   │   │   └── UserRepository.java      # User data access
│   │   │   ├── security/
│   │   │   │   └── SecurityConfig.java      # Security configuration
│   │   │   ├── service/
│   │   │   │   ├── StudentService.java      # Student service interface
│   │   │   │   ├── UserService.java         # User service interface
│   │   │   │   └── impl/
│   │   │   │       ├── StudentImpl.java     # Student service implementation
│   │   │   │       └── UserImpl.java        # User service implementation
│   │   │   └── util/
│   │   │       ├── JwtFilter.java           # JWT authentication filter
│   │   │       └── JwtUtil.java             # JWT utility methods
│   │   └── resources/
│   │       ├── application.yml              # Main application config
│   │       └── application-local.yml        # Local profile config
│   └── test/
│       └── java/                            # Test classes
├── pom.xml                                  # Maven dependencies
└── README.md                                # This file
```

## 🔌 API Endpoints

### User Endpoints

| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| POST | `/users/register` | Register a new user | Requires ADMIN role |
| POST | `/users/login` | Login and get JWT token | Requires ADMIN role |
| GET | `/users/getUsers` | Get all users | Requires ADMIN role |

#### Register User Request
```json
{
  "id": "U001",
  "username": "admin",
  "password": "password123",
  "role": "ADMIN"
}
```

#### Login Request
```json
{
  "username": "admin",
  "password": "password123"
}
```

#### Login Response
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY0...
```

### Student Endpoints

| Method | Endpoint | Description | Authentication |
|--------|----------|-------------|----------------|
| POST | `/student/add-student` | Add a new student | Required |
| GET | `/student/get-student` | Get all students | Required |
| DELETE | `/student/remove-student/{id}` | Delete a student by ID | Required |

#### Add Student Request
```json
{
  "id": "S001",
  "firstName": "John",
  "lastName": "Doe",
  "nic": "123456789V",
  "address": "123 Main Street",
  "phone": "0771234567",
  "city": "Colombo"
}
```

## 🗄️ Database Schema

### Users Table
- `id` (VARCHAR) - Primary Key
- `username` (VARCHAR) - Unique username
- `password` (VARCHAR) - Encrypted password
- `role` (VARCHAR) - User role (e.g., ADMIN, USER)

### Students Table
- `id` (VARCHAR) - Primary Key
- `first_name` (VARCHAR) - Student's first name
- `last_name` (VARCHAR) - Student's last name
- `nic` (VARCHAR) - National Identity Card number
- `address` (VARCHAR) - Student's address
- `phone` (VARCHAR) - Contact number
- `city` (VARCHAR) - City of residence

## 🔧 Development

### Building the Project

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package as JAR
mvn package

# Skip tests during build
mvn clean install -DskipTests
```

### Running in Development Mode

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

## 📝 Notes

- The application uses **Hibernate's auto-update** feature for database schema management
- **CORS** is enabled for cross-origin requests
- JWT tokens expire after **10 hours**
- All passwords are encrypted using **BCrypt**
- The application runs on port **8080** by default

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

Developed as part of Enterprise Applications coursework.

## 📧 Contact

For questions or support, please contact the development team.

---

**Version**: 1.0-SNAPSHOT  
**Last Updated**: January 2026
