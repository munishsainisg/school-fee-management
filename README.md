# Student Fee Management Service

## Overview

This project implements a backend service for managing students and collecting school fees. The application exposes REST APIs to register students, collect fee payments, and retrieve payment receipts.

The solution is built using **Spring Boot** and uses an **H2 in-memory database** for persistence. **Swagger** is included for API documentation and testing.


## Technology Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 In-Memory Database
- Maven
- Swagger / OpenAPI

---

## Features

The application provides the following APIs:

### Add Student
Registers a new student with student details.

### Collect Fee
Records a fee payment for an existing student and generates a receipt.

### Get Receipt
Retrieves receipt details using the receipt number.

---

## Running the Application
You can start the application using Maven. The application will start on port 8080

### Prerequisites
- Java 17
- Maven

### Steps

1. Clone the repository
2. Open the project in your IDE
3. Run the Spring Boot application

### Windows:
```bash
  .\mvnw.cmd spring-boot:run
```

### Mac/Linux:
```bash
  ./mvnw spring-boot:run
```

---

## API Documentation

### Swagger documentation:
Swagger URL : http://localhost:8080/swagger-ui.html

### Postman Collection
Postman collection to test the APIs is available at:
```
postmen/StudentFeeManagementService.postman_collection.json
```

---

# Database

The application uses an H2 in-memory database.

H2 console : http://localhost:8080/h2-console

Connection details:
```
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:studentdb
Username: sa
Password:
```

