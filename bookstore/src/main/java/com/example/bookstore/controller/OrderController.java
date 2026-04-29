package com.example.bookstore.controller;

import com.example.bookstore.model.*;
import com.example.bookstore.repository.*;
import com.example.bookstore.assembler.OrderModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing Order resources.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository repo;
    private final OrderModelAssembler assembler;

    /**
     * Constructs an OrderController with the given repository and assembler.
     *
     * @param repo the OrderRepository for database operations
     * @param assembler the OrderModelAssembler for converting entities to models
     */
    public OrderController(OrderRepository repo, OrderModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return a list of all orders
     */
    @GetMapping
    public List<Order> all() {
        return repo.findAll();
    }

    /**
     * Retrieves a single order by its ID with HATEOAS links.
     *
     * @param id the order's unique identifier
     * @return an EntityModel containing the order and HATEOAS links
     * @throws RuntimeException if the order is not found
     */
    @GetMapping("/{id}")
    public EntityModel<Order> one(@PathVariable Long id) {
        return assembler.toModel(repo.findById(id).orElseThrow());
    }

    /**
     * Creates a new order in the database.
     *
     * @param o the order to create
     * @return the created order
     */
    @PostMapping
    public Order create(@RequestBody Order o) {
        return repo.save(o);
    }

    /**
     * Marks an order as paid by updating its status.
     *
     * @param id the order's unique identifier
     * @return the updated order with PAID status
     * @throws RuntimeException if the order is not found
     */
    @PutMapping("/{id}/pay")
    public Order pay(@PathVariable Long id) {
        Order o = repo.findById(id).orElseThrow();
        o.setStatus(OrderStatus.PAID);
        return repo.save(o);
    }

    /**
     * Cancels an order by updating its status.
     *
     * @param id the order's unique identifier
     * @return the updated order with CANCELED status
     * @throws RuntimeException if the order is not found
     */
    @PutMapping("/{id}/cancel")
    public Order cancel(@PathVariable Long id) {
        Order o = repo.findById(id).orElseThrow();
        o.setStatus(OrderStatus.CANCELLED);
        return repo.save(o);
    }
    
    /**
     * Deletes an order from the database.
     *
     * @param id the order's unique identifier
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
    
    
}