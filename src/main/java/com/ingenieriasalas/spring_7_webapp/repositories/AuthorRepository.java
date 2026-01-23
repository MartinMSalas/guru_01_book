package com.ingenieriasalas.spring_7_webapp.repositories;

import com.ingenieriasalas.spring_7_webapp.domain.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
//
//    @Override
//    @EntityGraph(attributePaths = "books")
//    List<Author> findAll();
}
