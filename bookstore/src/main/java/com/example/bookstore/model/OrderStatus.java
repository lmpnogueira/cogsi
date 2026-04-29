package com.example.bookstore.model;

/**
 * Enum representing the status of an order.
 */
public enum OrderStatus {
    /** Order is pending and awaiting payment. */
    PENDING,
    
    /** Order has been paid. */
    PAID,
    
    /** Order has been cancelled. */
    CANCELLED
}