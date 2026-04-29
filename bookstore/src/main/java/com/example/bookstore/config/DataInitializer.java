package com.example.bookstore.config;

import com.example.bookstore.model.*;
import com.example.bookstore.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Initializer for setting up sample data in the database.
 */
@Configuration
public class DataInitializer {

    /**
     * Creates a CommandLineRunner bean that initializes sample data.
     * This runs on application startup and populates the database with sample books, clients, and orders.
     *
     * @param bookRepo the BookRepository for book persistence
     * @param clientRepo the ClientRepository for client persistence
     * @param orderRepo the OrderRepository for order persistence
     * @return a CommandLineRunner that initializes sample data
     */
    @Bean
    CommandLineRunner init(
            BookRepository bookRepo,
            ClientRepository clientRepo,
            OrderRepository orderRepo
    ) {
        return args -> {

            // Books
            Book book1 = new Book();
            book1.setTitle("Clean Code");
            book1.setAuthor("Robert C. Martin");
            book1.setPrice(30);
            bookRepo.save(book1);

            Book book2 = new Book();
            book2.setTitle("Effective Java");
            book2.setAuthor("Joshua Bloch");
            book2.setPrice(40);
            bookRepo.save(book2);

            // Client
            Client client = new Client();
            client.setName("John Doe");
            client.setEmail("john@demo.com");
            clientRepo.save(client);

            // Order
            Order order = new Order();
            order.setClient(client);
            order.setBooks(List.of(book1, book2));
            order.setStatus(OrderStatus.PENDING);

            orderRepo.save(order);
        };
    }
}