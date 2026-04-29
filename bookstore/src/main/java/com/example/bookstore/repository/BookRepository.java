package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Book entities.
 */
public interface BookRepository extends JpaRepository<Book, Long> {}