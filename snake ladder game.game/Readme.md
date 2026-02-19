# 🐍 Snake & Ladder -- State + Strategy Design

A clean, extensible implementation of the classic Snake and Ladder game
built using:

-   Java
-   Spring Boot
-   JPA (Hibernate)
-   H2 In-Memory Database
-   State Pattern
-   Strategy Pattern

------------------------------------------------------------------------

# 🚀 Features

-   Multi-player support
-   Persistent game state (H2 DB)
-   Clean separation of concerns
-   State-driven game lifecycle
-   Pluggable movement rules via Strategy pattern
-   REST API support

------------------------------------------------------------------------

# 🏗 Architecture Overview

Controller\
↓\
GameService (Orchestrator)\
↓\
State Pattern (Game lifecycle)\
↓\
Strategy Pattern (Movement rules)\
↓\
Repositories (Persistence)

------------------------------------------------------------------------

# 🧠 Design Patterns Used

## 1️⃣ State Pattern

Manages game lifecycle.

States: - CreatedState - InProgressState - FinishedState

Game decides which state to use based on:

    game.getStatus()

State object is selected via:

    GameStateFactory.getState(game.getStatus())

------------------------------------------------------------------------

## 2️⃣ Strategy Pattern

Encapsulates movement logic.

MovementStrategy\
└── ClassicMovementStrategy

Responsibilities: - Apply dice roll - Check snakes - Check ladders -
Return final position

------------------------------------------------------------------------

# 📂 Project Structure

snake-ladder/ │ ├── pom.xml │ └── src/main/java/com/example/snakeladder
│ ├── controller/ │ └── GameController.java │ ├── service/ │ ├──
GameService.java │ ├── DiceService.java │ ├── state/ │ │ ├──
GameState.java │ │ ├── CreatedState.java │ │ ├── InProgressState.java │
│ ├── FinishedState.java │ │ └── GameStateFactory.java │ │ │ └──
strategy/ │ ├── MovementStrategy.java │ └── ClassicMovementStrategy.java
│ ├── entity/ │ ├── Game.java │ ├── Player.java │ ├── Snake.java │ ├──
Ladder.java │ └── GameStatus.java │ └── repository/ ├──
GameRepository.java ├── PlayerRepository.java ├── SnakeRepository.java
└── LadderRepository.java

------------------------------------------------------------------------

# 🎮 Game Flow

1️⃣ Create Game\
POST /games

2️⃣ Add Players\
POST /games/{id}/players?name=Alice

3️⃣ Start Game\
POST /games/{id}/start

4️⃣ Roll Dice\
POST /games/{id}/roll

------------------------------------------------------------------------

# 💾 Database

-   H2 in-memory DB
-   Auto schema generation
-   Accessible at:

http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

------------------------------------------------------------------------

# 🔄 State Transitions

CREATED\
↓ startGame()\
IN_PROGRESS\
↓ player reaches 100\
FINISHED

------------------------------------------------------------------------

# 🔧 How to Run

1.  Clone repository

2.  Run:

    mvn spring-boot:run

3.  Test APIs using Postman

------------------------------------------------------------------------

# 📈 Future Improvements

-   DTO layer
-   Global exception handling
-   Swagger integration
-   Optimistic locking (@Version)
-   Event logging
-   WebSocket real-time multiplayer

------------------------------------------------------------------------

# 📜 License

This project is for educational and system design practice purposes.
