package com.ingenieriasalas.spring_7_webapp.services;

import com.ingenieriasalas.spring_7_webapp.domain.Book;

/*
 * Author: m
 * Date: 22/1/26
 * Project Name: guru-01-book
 * Description: beExcellent
 */
public interface BookService {

    Iterable<Book> findAll();

}
