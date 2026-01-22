package com.ingenieriasalas.spring_7_webapp.repositories;

import com.ingenieriasalas.spring_7_webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
