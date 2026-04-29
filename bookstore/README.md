# BookStore API 
# (Spring Boot + H2 + HATEOAS)

A simple **BookStore REST API** built with Spring Boot, Spring Data JPA, H2 database, and Spring HATEOAS.

This project is designed for **DevOps and Software Engineering courses**, demonstrating:
- RESTful API design and secure service architecture
- HATEOAS navigation and service discoverability
- In-memory database usage (H2) for controlled development and testing
- Programmatic data bootstrapping for reproducible environments
- Build automation, testing, and CI/CD readiness
- Secure development foundations, including future integration of authentication, authorization, validation, and vulnerability management
---

# Features

This project serves as a foundation for demonstrating REST API development with Spring Boot and can evolve into a DevSecOps-ready platform through the integration of authentication, authorization, secure coding practices, automated testing, dependency and vulnerability scanning, CI/CD pipelines, infrastructure automation, and more advanced business logic.

Current features:

- Manage Books
- Manage Clients
- Manage Orders (with relationships)
- HATEOAS-based API navigation
- H2 in-memory database
- Programmatic data initialization (no SQL scripts)
- H2 Console for database inspection

---

# Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Spring HATEOAS
- H2 Database
- Maven

---

# Running the Application

## Prerequisites

- Java 17 or higher
- Maven 3.6+

## Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`


## Accessing the API

### Root Endpoint
- **GET** `/` - API entry point with HATEOAS links

### Books
- **GET** `/books` - List all books
- **GET** `/books/{id}` - Get a specific book
- **POST** `/books` - Create a new book

### Clients
- **GET** `/clients` - List all clients
- **GET** `/clients/{id}` - Get a specific client
- **POST** `/clients` - Create a new client

### Orders
- **GET** `/orders` - List all orders
- **GET** `/orders/{id}` - Get a specific order
- **POST** `/orders` - Create a new order
- **PUT** `/orders/{id}/pay` - Mark an order as paid
- **PUT** `/orders/{id}/cancel` - Mark an order as cancelled
- **DELETE** `/orders/{id}` - Deletes an order from the database


### Info
- **GET** `/info/details` - Service information

### Health
- **GET** `/health` - Health check

## H2 Database Console

Access the H2 console at: `http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:bookstore`
- **Username**: `sa`
- **Password**: (leave empty)

## Sample Data

The application initializes with sample data:

### Books
- "Clean Code" by Robert C. Martin - $30.00
- "Effective Java" by Joshua Bloch - $40.00

### Clients
- John Doe (john@demo.com)

### Orders
- Order #1: John Doe ordered both books (status: PENDING)

## API Examples

### Create a Book
```bash
curl -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Design Patterns",
    "author": "Gang of Four",
    "price": 50.0
  }'
```

### Create a Client
```bash
curl -X POST http://localhost:8080/clients \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Smith",
    "email": "jane@example.com"
  }'
```

### Create an Order
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "client": {"id": 1},
    "books": [{"id": 1}, {"id": 2}],
    "status": "PENDING"
  }'
```

### Pay an Order
```bash
curl -X PUT http://localhost:8080/orders/1/pay
```

## Data Models

### Book
```json
{
  "id": 1,
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "price": 30.0
}
```

### Client
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@demo.com"
}
```

### Order
```json
{
  "id": 1,
  "client": {...},
  "books": [...],
  "status": "PENDING"
}
```

Order Status: `PENDING`, `PAID`, `CANCELLED`

## Project Structure

```
src/main/java/com/example/bookstore/
├── BookstoreApplication.java          # Main application class
├── assembler/                         # HATEOAS assemblers
│   ├── BookModelAssembler.java
│   ├── ClientModelAssembler.java
│   └── OrderModelAssembler.java
├── config/
│   └── DataInitializer.java           # Sample data initialization
├── controller/                        # REST controllers
│   ├── BookController.java
│   ├── ClientController.java
│   ├── InfoController.java
│   ├── OrderController.java
│   └── RootController.java
├── exception/
│   └── NotFoundException.java         # Custom exception
├── model/                             # JPA entities
│   ├── Book.java
│   ├── Client.java
│   ├── Order.java
│   └── OrderStatus.java
└── repository/                        # Spring Data repositories
    ├── BookRepository.java
    ├── ClientRepository.java
    └── OrderRepository.java
```

## Configuration

Key configuration in `application.properties`:
- Server port: 8080
- H2 console enabled at `/h2-console`
- In-memory database
- JPA with automatic DDL updates
- SQL logging enabled

## Testing

Run tests with:
```bash
mvn test
```

## Generate and Access Javadoc

Generate the Javadoc with:

```bash
mvn javadoc:javadoc
```

The generated documentation will be available at:

`target/site/apidocs/index.html`

Open the `index.html` file in your browser to explore the project’s classes, packages, and documented API structure.

## Building for Production

```bash
mvn clean package
java -jar target/bookstore-1.0.0.jar
```
---

## License

Educational use only

Luís Nogueira (lmn@isep.ipp.pt)
