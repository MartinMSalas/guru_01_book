package com.ingenieriasalas.spring_7_webapp.repositories;

import com.ingenieriasalas.spring_7_webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
