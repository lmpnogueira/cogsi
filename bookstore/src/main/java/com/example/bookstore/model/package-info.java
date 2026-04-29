/**
 * Entity models package for the Bookstore application.
 * 
 * <p>This package contains the JPA entity classes representing the core domain models:</p>
 * 
 * <ul>
 *   <li>{@link com.example.bookstore.model.Book} - Represents a book with title, author, and price</li>
 *   <li>{@link com.example.bookstore.model.Client} - Represents a customer with name and email</li>
 *   <li>{@link com.example.bookstore.model.Order} - Represents an order with client, books, and status</li>
 *   <li>{@link com.example.bookstore.model.OrderStatus} - Enum for order states: PENDING, PAID, CANCELLED</li>
 * </ul>
 * 
 * <p>All entities are mapped to database tables using Jakarta Persistence API (JPA) annotations.</p>
 */
package com.example.bookstore.model;
