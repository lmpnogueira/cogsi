package com.example.bookstore.controller;

import com.example.bookstore.model.Client;
import com.example.bookstore.repository.ClientRepository;
import com.example.bookstore.assembler.ClientModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Client resources.
 */
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository repo;
    private final ClientModelAssembler assembler;

    /**
     * Constructs a ClientController with the given repository and assembler.
     *
     * @param repo the ClientRepository for database operations
     * @param assembler the ClientModelAssembler for converting entities to models
     */
    public ClientController(ClientRepository repo, ClientModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of all clients
     */
    @GetMapping
    public List<Client> all() {
        return repo.findAll();
    }

    /**
     * Retrieves a single client by its ID with HATEOAS links.
     *
     * @param id the client's unique identifier
     * @return an EntityModel containing the client and HATEOAS links
     * @throws RuntimeException if the client is not found
     */
    @GetMapping("/{id}")
    public EntityModel<Client> one(@PathVariable Long id) {
        return assembler.toModel(repo.findById(id).orElseThrow());
    }

    /**
     * Creates a new client in the database.
     *
     * @param c the client to create
     * @return the created client
     */
    @PostMapping
    public Client create(@RequestBody Client c) {
        return repo.save(c);
    }
}