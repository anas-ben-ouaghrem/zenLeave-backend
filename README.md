# ZenLeave - Employee Leave Management Application

## Description
ZenLeave is a comprehensive employee leave management system designed to streamline the process of requesting, approving, and tracking employee leaves and exit permissions. The application provides a robust backend API for managing various types of leave requests, team exit permissions, and user management with role-based access control.

## Technologies Used
- **Java 17**
- **Spring Boot 3.1.6**
- **Spring Data JPA** - For database operations
- **Spring Security** - For authentication and authorization
- **JWT (JSON Web Tokens)** - For secure token-based authentication
- **TOTP** - For two-factor authentication
- **MySQL** - Database
- **Hibernate Validator** - For data validation
- **Spring AOP** - For aspect-oriented programming
- **OpenAPI/Swagger** - For API documentation
- **Spring Mail** - For email notifications
- **Lombok** - To reduce boilerplate code
- **Maven** - For dependency management and build

## Features
- **User Management**
  - User registration and authentication
  - Role-based access control (ADMIN, MANAGER, USER)
  - Two-factor authentication
  - JWT-based secure authentication

- **Leave Management**
  - Create, view, update, and delete leave requests
  - Different types of leave (vacation, sick leave, etc.)
  - Leave approval workflow
  - Leave history tracking

- **Team Exit Permissions**
  - Request team exit permissions
  - Manager approval workflow
  - View and filter permissions by team or user

- **External Authorizations (Day Leave)**
  - Manage external authorization requests
  - Approval workflow

- **API Documentation**
  - Comprehensive API documentation using OpenAPI/Swagger

## Installation and Setup

### Prerequisites
- Java 17 or higher
- MySQL Server
- Maven

### Steps
1. **Clone the repository**
   ```
   git clone https://github.com/yourusername/Leave-Management-App.git
   cd Leave-Management-App
   ```

2. **Configure the database**
   - Create a MySQL database
   - Update the database configuration in `src/main/resources/application.properties` or `application.yml`

3. **Build the application**
   ```
   mvn clean install
   ```

4. **Run the application**
   ```
   mvn spring-boot:run
   ```
   or
   ```
   java -jar target/ZenLeave-0.0.1-SNAPSHOT.jar
   ```

5. **Access the application**
   - The application will be available at `http://localhost:8081`
   - API documentation will be available at `http://localhost:8081/swagger-ui.html`

## Usage
- Use the Swagger UI to explore and test the API endpoints
- Authenticate using the `/api/v1/auth/authenticate` endpoint to get a JWT token
- Use the token in the Authorization header for subsequent requests

## API Documentation
The API documentation is available through Swagger UI when the application is running. Access it at:
```
http://localhost:8081/swagger-ui.html
```

## Contributors
- Anas Ben Ouaghrem (anas.ouaghrem@esprit.tn)

