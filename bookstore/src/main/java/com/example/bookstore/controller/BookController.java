package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.assembler.BookModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Book resources.
 */
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repo;
    private final BookModelAssembler assembler;

    /**
     * Constructs a BookController with the given repository and assembler.
     *
     * @param repo the BookRepository for database operations
     * @param assembler the BookModelAssembler for converting entities to models
     */
    public BookController(BookRepository repo, BookModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    /**
     * Retrieves all books from the database.
     *
     * @return a list of all books
     */
    @GetMapping
    public List<Book> all() {
        return repo.findAll();
    }

    /**
     * Retrieves a single book by its ID with HATEOAS links.
     *
     * @param id the book's unique identifier
     * @return an EntityModel containing the book and HATEOAS links
     * @throws RuntimeException if the book is not found
     */
    @GetMapping("/{id}")
    public EntityModel<Book> one(@PathVariable Long id) {
        return assembler.toModel(repo.findById(id).orElseThrow());
    }

    /**
     * Creates a new book in the database.
     *
     * @param b the book to create
     * @return the created book
     */
    @PostMapping
    public Book create(@RequestBody Book b) {
        return repo.save(b);
    }
}