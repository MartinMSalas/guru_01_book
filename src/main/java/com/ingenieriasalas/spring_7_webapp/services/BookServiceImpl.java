package com.ingenieriasalas.spring_7_webapp.services;

import com.ingenieriasalas.spring_7_webapp.domain.Book;
import com.ingenieriasalas.spring_7_webapp.repositories.BookRepository;
import org.springframework.stereotype.Service;

/*
 * Author: m
 * Date: 22/1/26
 * Project Name: guru-01-book
 * Description: beExcellent
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }
}
