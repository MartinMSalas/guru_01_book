package com.ingenieriasalas.spring_7_webapp.bootstrap;

import com.ingenieriasalas.spring_7_webapp.domain.Author;
import com.ingenieriasalas.spring_7_webapp.domain.Book;
import com.ingenieriasalas.spring_7_webapp.domain.Publisher;
import com.ingenieriasalas.spring_7_webapp.repositories.AuthorRepository;
import com.ingenieriasalas.spring_7_webapp.repositories.BookRepository;
import com.ingenieriasalas.spring_7_webapp.repositories.PublisherRepository;
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
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
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

        Publisher pub = new Publisher();
        pub.setPublisherName("Pub pub publishing");
        pub.setState("Catamarca");

        Author ericSaved = authorRepository.save(eric);
        Author marcusSaved = authorRepository.save(marcus);

        Book turnerSaved = bookRepository.save(turnerDiaries);
        Book meditationsSaved = bookRepository.save(meditations);

        Publisher pubSaved = publisherRepository.save(pub);

        ericSaved.getBooks().add(turnerSaved);
        marcusSaved.getBooks().add(meditationsSaved);

        turnerSaved.setPublisher(pub);
        meditationsSaved.setPublisher(pub);

        turnerSaved = bookRepository.save(turnerDiaries);
        meditationsSaved = bookRepository.save(meditations);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
        System.out.println("Turner: " + turnerSaved);
        System.out.println("Medidations: " + meditationsSaved);
        System.out.println("Publisher: " + pubSaved);

    }
}
