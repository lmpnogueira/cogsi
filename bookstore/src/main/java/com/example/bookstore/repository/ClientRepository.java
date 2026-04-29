package com.example.bookstore.repository;

import com.example.bookstore.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Client entities.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {}