# Login Service (Authentication & Authorization)

## 1. Overview

This project implements a **Login / Authentication Service** using **Spring Boot**. It provides APIs for user login, credential validation, and secure authentication flow. The service is designed to be **simple, extensible, and interview-ready**, focusing on clean layering, security fundamentals, and real-world backend practices.

The system can be easily extended to support **JWT-based authentication**, **OAuth**, or **SSO**.

---

## 2. Requirements

### Functional Requirements

1. User should be able to log in using username/email and password
2. System should validate credentials securely
3. On successful login:

    * Authentication response should be returned
4. On failure:

    * Proper error should be returned (invalid credentials / user not found)

### Non-Functional Requirements

* Passwords must not be stored in plain text
* Clear separation of concerns (Controller, Service, Repository)
* Secure and scalable design

---

## 3. Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Spring Security (basic usage)
* Hibernate
* Maven
* REST APIs
* Relational Database (H2 / MySQL compatible)

---

## 4. High Level Flow

1. Client sends login request with credentials
2. System fetches user by username/email
3. Password is validated using hashing
4. On success:

    * Authentication success response returned
5. On failure:

    * Error response returned

---

## 5. Entity Design

### User

Represents a registered user in the system.

| Field    | Type          | Description           |
| -------- | ------------- | --------------------- |
| id       | Long          | Primary key           |
| username | String        | Unique username/email |
| password | String        | Encrypted password    |
| role     | String / Enum | User role             |
| isActive | Boolean       | User account status   |

---

## 6. API Design

### Login API

**Endpoint**

```
POST /login
```

**Request Body**

```
{
  "username": "user@example.com",
  "password": "password123"
}
```

**Success Response**

```
{
  "message": "Login successful",
  "status": "SUCCESS"
}
```

**Failure Response**

```
{
  "message": "Invalid credentials",
  "status": "FAILED"
}
```

---

## 7. Service Layer Logic

### LoginService.authenticate(...)

**Step-by-step logic:**

1. Fetch user by username/email
2. If user not found → throw exception
3. Compare encrypted password using password encoder
4. If password mismatch → authentication failure
5. If match → authentication success

---

## 8. Security Considerations

* Passwords stored using hashing (e.g., BCrypt)
* No plain-text password exposure
* Error messages do not leak sensitive info

---



## 9. Failure Scenarios Covered

* Invalid username
* Incorrect password
* Inactive user account

---

## 10. Possible Improvements (Interview Discussion)

* JWT token generation
* Refresh tokens
* Role-based access control (RBAC)
* OAuth2 / SSO integration
* Rate limiting login attempts
* Account lock on multiple failures

---

## 11. How to Run

```
mvn spring-boot:run
```

Test APIs using Postman or curl.

---

