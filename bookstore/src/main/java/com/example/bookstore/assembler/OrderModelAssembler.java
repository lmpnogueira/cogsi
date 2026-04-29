package com.example.bookstore.assembler;

import com.example.bookstore.controller.OrderController;
import com.example.bookstore.model.Order;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Assembler for converting Order entities to EntityModel instances.
 */
@Component
public class OrderModelAssembler {

    /**
     * Converts an Order entity to an EntityModel with HATEOAS links.
     *
     * @param order the order to convert
     * @return an EntityModel containing the order and HATEOAS links
     */
    public EntityModel<Order> toModel(Order order) {
        return EntityModel.of(order,
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders"));
    }
}