# Order Management System (Spring Boot)

## 1. Overview

This project is a **basic Order Management System** implemented using **Spring Boot, JPA/Hibernate, and H2/MySQL-compatible configuration**. It demonstrates an end-to-end backend design for placing an order, reserving inventory, handling payment success/failure, and persisting order and order items.

The project is **interview‑ready** and intentionally kept simple to highlight:

* Entity modeling
* Transaction management
* Service orchestration
* Failure handling & rollback

---

## 2. Requirements

### Functional Requirements

1. User should be able to place an order with multiple items
2. System should:

    * Create an order
    * Reserve inventory for each item
    * Handle payment success/failure
    * Persist order items
3. On failure:

    * Inventory should be rolled back
    * Order status should reflect failure

### Non‑Functional Requirements

* Atomic operation for order placement
* Clear order lifecycle states
* Easy extensibility for payment gateway / async processing

---

## 3. Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* REST APIs
* Relational Database (H2 / MySQL compatible)

---

## 4. High Level Flow

1. Client calls **Place Order API**
2. Order is created with status `ORDER_CREATED`
3. Inventory is reserved for all items
4. Payment is attempted
5. On success:

    * OrderItems are persisted
    * Order completes
6. On failure:

    * Inventory is rolled back
    * Order status updated

---

## 5. Entity Design

### Order

Represents a user order.

| Field       | Type    | Description           |
| ----------- | ------- | --------------------- |
| orderId     | UUID    | Primary key           |
| userId      | String  | User placing order    |
| totalCost   | Integer | Total order cost      |
| orderStatus | Enum    | Order lifecycle state |

### OrderItem

Represents individual items in an order.

| Field     | Type    | Description        |
| --------- | ------- | ------------------ |
| id        | Long    | Primary key        |
| orderId   | UUID    | FK to Order        |
| productId | String  | Product identifier |
| quantity  | Integer | Quantity ordered   |
| cost      | Integer | Cost per item      |

### Inventory

Tracks product stock.

| Field          | Type    | Description   |
| -------------- | ------- | ------------- |
| productId      | String  | Primary key   |
| availableStock | Integer | Current stock |

### OrderStatus (Enum)

```
ORDER_CREATED
INVENTORY_RESERVED
ORDER_FAILED
ORDER_CANCELLED
```

---

## 6. API Design

### Place Order

**Endpoint**

```
POST /orders
```

**Request Body**

```
{
  "userId": "user-123",
  "items": [
    {
      "productId": "p1",
      "quantity": 2,
      "cost": 100
    }
  ]
}
```

**Response**

```
Order ID (UUID)
```

---

## 7. Service Layer Logic

### OrderService.placeOrder(...)

**Step-by-step logic:**

1. Calculate total cost
2. Create Order with `ORDER_CREATED`
3. Reserve inventory for each item
4. Update order status to `INVENTORY_RESERVED`
5. Simulate payment (80% success)
6. On payment failure:

    * Rollback inventory
    * Mark order as `ORDER_FAILED`
7. Persist `OrderItem`s
8. Return Order ID

**Transaction Management**

* `@Transactional` ensures atomicity
* Any runtime exception triggers rollback

---

## 8. Inventory Handling

### InventoryService

* `reserveStock(productId, quantity)`
* `releaseStock(productId, quantity)`

Used to ensure stock consistency during failures.


---

## 9. Failure Scenarios Covered

* Inventory shortage
* Payment failure
* Partial order creation

All handled via rollback + status update.

---

## 10. Possible Improvements (Interview Discussion)

* Async payment processing
* Saga / Orchestrator pattern
* Idempotency for APIs
* Optimistic locking on inventory
* Event-driven design (Kafka)
* Separate Payment Service

---

## 11. How to Run

```
mvn spring-boot:run
```

Access APIs via Postman or curl.


