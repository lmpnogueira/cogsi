package com.example.bookstore.assembler;

import com.example.bookstore.controller.ClientController;
import com.example.bookstore.model.Client;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Assembler for converting Client entities to EntityModel instances.
 */
@Component
public class ClientModelAssembler {

    /**
     * Converts a Client entity to an EntityModel with HATEOAS links.
     *
     * @param client the client to convert
     * @return an EntityModel containing the client and HATEOAS links
     */
    public EntityModel<Client> toModel(Client client) {
        return EntityModel.of(client,
                linkTo(methodOn(ClientController.class).one(client.getId())).withSelfRel(),
                linkTo(methodOn(ClientController.class).all()).withRel("clients"));
    }
}