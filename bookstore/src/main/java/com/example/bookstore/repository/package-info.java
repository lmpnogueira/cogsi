/**
 * Repository package for the Bookstore application.
 * 
 * <p>This package contains the Spring Data JPA repository interfaces for data access:</p>
 * 
 * <ul>
 *   <li>{@link com.example.bookstore.repository.BookRepository} - Data access for Book entities</li>
 *   <li>{@link com.example.bookstore.repository.ClientRepository} - Data access for Client entities</li>
 *   <li>{@link com.example.bookstore.repository.OrderRepository} - Data access for Order entities</li>
 * </ul>
 * 
 * <p>Repositories extend {@link org.springframework.data.jpa.repository.JpaRepository} 
 * to provide standard CRUD operations and query methods for their respective entities.</p>
 */
package com.example.bookstore.repository;
