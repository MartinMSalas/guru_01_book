package com.ingenieriasalas.spring_7_webapp.services;

import com.ingenieriasalas.spring_7_webapp.domain.Author;
import com.ingenieriasalas.spring_7_webapp.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/*
 * Author: M
 * Date: 22-Jan-26
 * Project Name: spring-7-webapp
 * Description: beExcellent
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Iterable<Author> findAll() {

        return authorRepository.findAll();

    }
}
