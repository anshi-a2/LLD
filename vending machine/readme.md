# Vending Machine Backend Service

## 1. Overview

This project implements a **Vending Machine backend system** using **Spring Boot**, designed specifically for **machine coding and low-level design (LLD) interviews**. The system models real-world vending machine behavior including item selection, money insertion, inventory validation, dispensing items, and refund handling.

The focus is on:

* Clean object-oriented design
* State-driven flow
* Clear separation of responsibilities
* Transaction-safe operations

---

## 2. Requirements

### Functional Requirements

1. Vending machine should display available items with price and quantity
2. User should be able to:

    * Select an item
    * Insert money
    * Purchase item
3. System should:

    * Validate sufficient balance
    * Validate item availability
    * Dispense item on success
    * Return change if required
4. On failure:

    * Refund inserted amount

### Non-Functional Requirements

* Consistent inventory state
* Easy extensibility for new items / denominations
* Deterministic and predictable state transitions

---

## 3. Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* REST APIs
* In-memory / Relational DB (H2 / MySQL compatible)

---

## 4. High-Level Flow

1. User selects an item
2. User inserts money
3. System validates:

    * Item availability
    * Inserted amount ≥ item price
4. System dispenses item
5. Change is returned
6. Inventory is updated

---

## 5. Core Domain Entities

### Item

| Field    | Type    | Description     |
| -------- | ------- | --------------- |
| itemId   | String  | Primary key     |
| name     | String  | Item name       |
| price    | Integer | Item price      |
| quantity | Integer | Available stock |

### Transaction

| Field          | Type    | Description                 |
| -------------- | ------- | --------------------------- |
| transactionId  | UUID    | Primary key                 |
| itemId         | String  | Selected item               |
| amountInserted | Integer | Total inserted amount       |
| status         | Enum    | SUCCESS / FAILED / REFUNDED |

---

## 6. Machine State Model

The vending machine follows a **state-driven design**:

```
IDLE → ITEM_SELECTED → MONEY_INSERTED → DISPENSED → IDLE
```

On failure at any step → **REFUND → IDLE**

---

## 7. API Design

### Get Items

```
GET /items
```

### Select Item

```
POST /select/{itemId}
```

### Insert Money

```
POST /insert
```

**Request Body**

```
{
  "amount": 50
}
```

### Purchase Item

```
POST /purchase
```

---

## 8. Core Logic (Purchase Flow)

### VendingMachineService.purchase()

**Step-by-step logic:**

1. Validate selected item
2. Validate available quantity
3. Validate inserted money
4. Deduct item quantity
5. Dispense item
6. Calculate and return change
7. Reset machine state

All operations are atomic.

---

## 9. Change Calculation

* Change is calculated as:

```
change = amountInserted - itemPrice
```

* Returned using supported denominations


---

## 10. Failure Scenarios Covered

* Insufficient balance
* Item out of stock
* Invalid item selection

All failures trigger refund and state reset.

---

## 11. Possible Improvements (Interview Discussion)

* Support for multiple currencies
* Cash denomination inventory
* Concurrent machine access handling
* Event-driven dispense notifications
* Persistent transaction history

---

## 12. How to Run

```
mvn spring-boot:run
```

Use Postman or curl to test APIs.

---

