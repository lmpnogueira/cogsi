package com.example.bookstore.controller;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Controller for the root endpoint.
 */
@RestController
public class RootController {

    /**
     * Provides the root API entry point with HATEOAS links to all major resources.
     *
     * @return a RepresentationModel with links to books, clients, orders, health, and info
     */
    @GetMapping("/")
    public RepresentationModel<?> root() {

        RepresentationModel<?> model = new RepresentationModel<>();

        model.add(linkTo(methodOn(BookController.class).all()).withRel("books"));
        model.add(linkTo(methodOn(ClientController.class).all()).withRel("clients"));
        model.add(linkTo(methodOn(OrderController.class).all()).withRel("orders"));

        model.add(linkTo(methodOn(RootController.class).health()).withRel("health"));
        model.add(linkTo(methodOn(RootController.class).info()).withRel("info"));

        return model;
    }

    /**
     * Health endpoint providing service status.
     * Can be used by monitoring tools or for service discovery.
     *
     * @return a RepresentationModel with the service status
     */
    @GetMapping("/health")
    public RepresentationModel<?> health() {
        RepresentationModel<?> model = new RepresentationModel<>();
        model.add(linkTo(methodOn(RootController.class).root()).withRel("self"));
        model.add(org.springframework.hateoas.Link.of("UP").withRel("status"));
        return model;
    }

    /**
     * Info endpoint providing service details.
     *
     * @return a RepresentationModel with the service information
     */
    @GetMapping("/info")
    public RepresentationModel<?> info() {
        RepresentationModel<?> model = new RepresentationModel<>();
        model.add(linkTo(methodOn(RootController.class).root()).withRel("self"));
        model.add(org.springframework.hateoas.Link.of("BookStore API").withRel("service"));
        return model;
    }
}