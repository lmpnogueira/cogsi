package com.example.bookstore.assembler;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.model.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Assembler for converting Book entities to EntityModel instances.
 */
@Component
public class BookModelAssembler {

    /**
     * Converts a Book entity to an EntityModel with HATEOAS links.
     *
     * @param book the book to convert
     * @return an EntityModel containing the book and HATEOAS links
     */
    public EntityModel<Book> toModel(Book book) {
        return EntityModel.of(book,
                linkTo(methodOn(BookController.class).one(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).all()).withRel("books"));
    }
}