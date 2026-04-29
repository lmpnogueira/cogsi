/**
 * Assembler package for the Bookstore application.
 * 
 * <p>This package contains assembler classes responsible for converting entity objects 
 * into HATEOAS-compliant EntityModel objects with hypermedia links:</p>
 * 
 * <ul>
 *   <li>{@link com.example.bookstore.assembler.BookModelAssembler} - Converts Book entities to EntityModel with HATEOAS links</li>
 *   <li>{@link com.example.bookstore.assembler.ClientModelAssembler} - Converts Client entities to EntityModel with HATEOAS links</li>
 *   <li>{@link com.example.bookstore.assembler.OrderModelAssembler} - Converts Order entities to EntityModel with HATEOAS links</li>
 * </ul>
 * 
 * <p>The assemblers implement the Assembler pattern to separate the concerns of entity 
 * representation from REST API representation.</p>
 */
package com.example.bookstore.assembler;
