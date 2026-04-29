package com.example.bookstore.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity representing an Order in the bookstore.
 */
@Entity
@Table(name = "orders")
public class Order {

    /** The unique identifier for the order. */
    @Id
    @GeneratedValue
    private Long id;

    /** The client who placed the order. */
    @ManyToOne
    private Client client;

    /** The books included in the order. */
    @ManyToMany
    private List<Book> books;

    /** The current status of the order. */
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    /**
     * Default constructor for Order.
     */
    public Order() {}

    /**
     * Gets the order's unique identifier.
     *
     * @return the order id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the order's unique identifier.
     *
     * @param id the order id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the client associated with the order.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the client for the order.
     *
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Gets the books in the order.
     *
     * @return the list of books
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Sets the books for the order.
     *
     * @param books the list of books to set
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /**
     * Gets the current status of the order.
     *
     * @return the order status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the order.
     *
     * @param status the status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}