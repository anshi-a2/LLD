🎬 BookMyShow – Backend Low Level Design (LLD)
==============================================

Overview
--------

This repository contains a **Low Level Design (LLD)** implementation of a **BookMyShow-like ticket booking system**, designed from a **backend SDE-2 interview perspective**.

The focus is on:

*   Correct domain modeling

*   Concurrency-safe seat booking

*   Clean separation of concerns

*   Transactional consistency

*   Interview-grade backend architecture


The implementation uses **Java, Spring Boot, JPA, and H2 (in-memory database)**.

Problem Statement
-----------------

Design and implement the backend for a movie ticket booking system where:

*   Users can browse movies and shows

*   Users can view seat availability for a show

*   Users can book seats

*   The system must prevent **double booking under high concurrency**


Requirements
------------

### Functional Requirements

*   List movies

*   List shows for a movie

*   View seat availability for a show

*   Book one or more seats for a show

*   Confirm booking after successful processing


### Non-Functional Requirements

*   **Strong consistency** for seat booking

*   **High concurrency handling**

*   Atomic seat allocation

*   Low latency seat availability

*   Clear transactional boundaries


### Out of Scope

*   Payment gateway integration

*   Refunds & cancellations

*   Promotions & offers

*   Dynamic pricing

*   User authentication


High Level Architecture
-----------------------

```pgsql
Client
  |
Controller Layer (REST APIs)
  |
Service Layer (Business Logic)
  |
Repository Layer (JPA)
  |
H2 Database
```
Core Design Concepts
--------------------

### 1\. Seat Booking Strategy (Most Important)

Seats are **not booked directly**.

Instead, booking is handled using a **ShowSeat** abstraction:

*   Each seat for every show has its own state

*   Prevents conflicts across different shows

*   Enables fine-grained locking


### 2\. Key Entities

*   **User** – Person booking tickets

*   **Movie** – Movie metadata

*   **Theatre** – Physical theatre

*   **Screen** – Screen inside a theatre

*   **Seat** – Physical seat layout

*   **Show** – Movie + Screen + Timing

*   **ShowSeat** – Seat availability per show

*   **Booking** – Booking transaction


### 3\. Seat State Machine

```pgsql
AVAILABLE → LOCKED → BOOKED
```
*   AVAILABLE: Free to book

*   LOCKED: Temporarily held during booking

*   BOOKED: Permanently booked


Database Design (Relational)
----------------------------

### Key Tables

*   user

*   movie

*   theatre

*   screen

*   seat

*   show

*   show\_seat

*   booking


### Critical Table: show\_seat

Stores seat availability **per show**, enabling:

*   Concurrency control

*   Accurate availability checks

*   Independent bookings across shows


API Design
----------

### Fetch Movies

```pgsql
GET /movies
```

### Fetch Shows for a Movie

```pgsql
GET /movies/{movieId}/shows
```
### Fetch Seat Availability

```pgsql
GET /shows/{showId}/seats
```
### Book Seats

```pgsql
POST /bookings
```
**Request**

```pgsql
{
  "userId": "uuid",
  "showId": "uuid",
  "seatIds": ["uuid1", "uuid2"]
}



```
Concurrency Handling
--------------------

### Problem

Multiple users attempting to book the same seat simultaneously.

### Solution

*   **Database-level pessimistic locking**

*   **Transactional booking**

*   Seat rows are locked before updating status


This ensures:

*   No double booking

*   Atomic seat allocation

*   Safe concurrent access


Transaction Flow (Booking)
--------------------------

1.  Start DB transaction

2.  Lock requested seats

3.  Validate availability

4.  Mark seats as BOOKED

5.  Create booking record

6.  Commit transaction


If any step fails → rollback

Error Handling & Edge Cases
---------------------------

*   Partial seat availability

*   Concurrent booking attempts

*   Transaction rollback

*   Invalid seat selection

*   Stale seat data


Tech Stack
----------

*   Java

*   Spring Boot

*   Spring Data JPA

*   H2 (In-Memory Database)

*   REST APIs


Why This Design Works Well in Interviews
----------------------------------------

*   Demonstrates **real-world concurrency handling**

*   Shows understanding of **transactional consistency**

*   Clean separation of responsibilities

*   Scales conceptually to production systems

*   Matches expectations for **SDE-2 backend roles**


Possible Enhancements
---------------------

*   Seat locking with timeout using scheduler

*   Redis caching for seat availability

*   Payment workflow integration

*   Booking cancellation & refund support

*   Idempotent booking APIs

*   Horizontal scaling strategy