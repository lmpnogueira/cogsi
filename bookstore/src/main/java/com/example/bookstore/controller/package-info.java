/**
 * REST Controllers package for the Bookstore application.
 * 
 * <p>This package contains the REST API controllers that handle HTTP requests and responses:</p>
 * 
 * <ul>
 *   <li>{@link com.example.bookstore.controller.BookController} - Manages book CRUD operations at /books</li>
 *   <li>{@link com.example.bookstore.controller.ClientController} - Manages client CRUD operations at /clients</li>
 *   <li>{@link com.example.bookstore.controller.OrderController} - Manages order CRUD operations and payment at /orders</li>
 *   <li>{@link com.example.bookstore.controller.RootController} - Provides root API entry point with HATEOAS links</li>
 *   <li>{@link com.example.bookstore.controller.InfoController} - Provides service information details</li>
 * </ul>
 * 
 * <p>All controllers follow REST conventions and return HATEOAS-compliant responses with hypermedia links.</p>
 */
package com.example.bookstore.controller;
