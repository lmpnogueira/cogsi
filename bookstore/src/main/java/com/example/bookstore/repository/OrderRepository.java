package com.example.bookstore.repository;

import com.example.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Order entities.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {}