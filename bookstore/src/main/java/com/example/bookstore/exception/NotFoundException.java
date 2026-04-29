package com.example.bookstore.exception;

/**
 * Exception thrown when a requested resource is not found.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Constructs a NotFoundException with the specified message.
     *
     * @param msg the error message
     */
    public NotFoundException(String msg) {
        super(msg);
    }
}