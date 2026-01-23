package com.ingenieriasalas.spring_7_webapp.repositories;

import com.ingenieriasalas.spring_7_webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * Author: m
 * Date: 22/1/26
 * Project Name: guru-01-book
 * Description: beExcellent
 */
@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
