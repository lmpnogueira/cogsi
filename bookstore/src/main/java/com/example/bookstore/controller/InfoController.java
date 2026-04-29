package com.example.bookstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Controller for providing information about the service.
 */
@RestController
public class InfoController {

    /** The name of the service. */
    @Value("${spring.application.name}")
    private String serviceName;

    /** The version of the service. */
    @Value("${service.version}")
    private String version;

    /** The environment in which the service is running. */
    @Value("${service.environment}")
    private String environment;

    /**
     * Provides detailed information about the service.
     *
     * @return a RepresentationModel with service name, version, and environment
     */
    @GetMapping("/info/details")
    public RepresentationModel<?> details() {

        RepresentationModel<?> model = new RepresentationModel<>();

        model.add(org.springframework.hateoas.Link.of(serviceName).withRel("service"));
        model.add(org.springframework.hateoas.Link.of(version).withRel("version"));
        model.add(org.springframework.hateoas.Link.of(environment).withRel("environment"));

        model.add(linkTo(methodOn(RootController.class).root()).withRel("root"));

        return model;
    }
}