package com.example.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity representing a Client in the bookstore.
 */
@Entity
public class Client {

    /** The unique identifier for the client. */
    @Id
    @GeneratedValue
    private Long id;

    /** The name of the client. */
    private String name;
    
    /** The email address of the client. */
    private String email;

    /**
     * Default constructor for Client.
     */
    public Client() {}

    /**
     * Gets the client's unique identifier.
     *
     * @return the client id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the client's unique identifier.
     *
     * @param id the client id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the client's name.
     *
     * @return the client name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the client's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the client's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the client's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}