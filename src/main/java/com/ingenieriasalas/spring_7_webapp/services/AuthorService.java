package com.ingenieriasalas.spring_7_webapp.services;

import com.ingenieriasalas.spring_7_webapp.domain.Author;

import java.util.Set;

/*
 * Author: M
 * Date: 22-Jan-26
 * Project Name: spring-7-webapp
 * Description: beExcellent
 */
public interface AuthorService {

    public Iterable<Author> findAll();

}
