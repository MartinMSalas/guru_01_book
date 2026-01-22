package com.ingenieriasalas.spring_7_webapp.bootstrap;

import com.ingenieriasalas.spring_7_webapp.domain.Author;
import com.ingenieriasalas.spring_7_webapp.domain.Book;
import com.ingenieriasalas.spring_7_webapp.repositories.AuthorRepository;
import com.ingenieriasalas.spring_7_webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
 * Author: m
 * Date: 22/1/26
 * Project Name: guru-01-book
 * Description: Martin for Ingenieria Salas
 */
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Pocholo");

        Author marcus = new Author();
        marcus.setFirstName("Marcus");
        marcus.setLastName("Aurelius");

        Book turnerDiaries = new Book();
        turnerDiaries.setTitle("The Turner Diaries");
        turnerDiaries.setIsbn("9780692943656");

        Book meditations = new Book();
        meditations.setTitle("Marcus Aurelius meditations");
        meditations.setIsbn("1441221124412");

        Author ericSaved = authorRepository.save(eric);
        Author marcusSaved = authorRepository.save(marcus);
        Book turnerSaved = bookRepository.save(turnerDiaries);
        Book meditationsSaved = bookRepository.save(meditations);

        ericSaved.getBooks().add(turnerSaved);
        marcusSaved.getBooks().add(meditationsSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
    }
}
