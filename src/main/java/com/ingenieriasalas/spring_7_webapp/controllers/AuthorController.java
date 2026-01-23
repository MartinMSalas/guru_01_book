package com.ingenieriasalas.spring_7_webapp.controllers;

import com.ingenieriasalas.spring_7_webapp.services.AuthorService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Author: M
 * Date: 22-Jan-26
 * Project Name: spring-7-webapp
 * Description: beExcellent
 */
@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public Model getAuthors(Model model) {

        model.addAttribute("authors", authorService.findAll());

        return model;
    }
}
