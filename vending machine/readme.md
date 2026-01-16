🥤 Vending Machine – Low Level Design
-------------------------------------

### Overview

This repository contains a **Low Level Design implementation of a Vending Machine**, created from a **backend SDE-2 interview perspective**.

The design focuses on:

*   State-driven behavior

*   Clean object-oriented principles

*   Extensibility and correctness


### Problem Statement

Design a vending machine that:

*   Displays items

*   Accepts coins

*   Dispenses items

*   Returns change

*   Prevents invalid operations


### Key Design Approach

#### State Machine

The vending machine is implemented using the **State Design Pattern** to manage transitions between:

*   IDLE

*   HAS\_MONEY

*   DISPENSING


Each state controls allowed operations, ensuring correctness.

### Core Components

*   VendingMachine

*   Inventory

*   Item

*   State interface

*   Concrete states (Idle, HasMoney, Dispense)


### State Flow

```pgsql
IDLE → HAS_MONEY → DISPENSING → IDLE
```
### Edge Cases Handled

*   Item out of stock

*   Insufficient funds

*   Refund before dispense

*   Invalid operations per state