package com.ingenieriasalas.spring_7_webapp.controllers;

import com.ingenieriasalas.spring_7_webapp.services.BookService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Author: m
 * Date: 22/1/26
 * Project Name: guru-01-book
 * Description: beExcellent
 */
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public Model getBooks(Model model) {

        model.addAttribute("books", bookService.findAll());

        return model;
    }
}
