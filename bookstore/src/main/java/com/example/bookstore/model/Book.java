package com.example.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity representing a Book in the bookstore.
 */
@Entity
public class Book {

    /** The unique identifier for the book. */
    @Id
    @GeneratedValue
    private Long id;

    /** The title of the book. */
    private String title;
    
    /** The author of the book. */
    private String author;
    
    /** The price of the book. */
    private double price;

    /**
     * Default constructor for Book.
     */
    public Book() {}

    /**
     * Gets the book's unique identifier.
     *
     * @return the book id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the book's unique identifier.
     *
     * @param id the book id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the book's title.
     *
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the book's title.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the book's author.
     *
     * @return the author name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the book's author.
     *
     * @param author the author name to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the book's price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the book's price.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
}