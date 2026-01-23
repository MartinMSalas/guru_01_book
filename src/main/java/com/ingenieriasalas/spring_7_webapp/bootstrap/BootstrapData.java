package com.ingenieriasalas.spring_7_webapp.bootstrap;

import com.ingenieriasalas.spring_7_webapp.domain.Author;
import com.ingenieriasalas.spring_7_webapp.domain.Book;
import com.ingenieriasalas.spring_7_webapp.domain.Publisher;
import com.ingenieriasalas.spring_7_webapp.repositories.AuthorRepository;
import com.ingenieriasalas.spring_7_webapp.repositories.BookRepository;
import com.ingenieriasalas.spring_7_webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {

        /* ========= Publisher ========= */
        Publisher pub = new Publisher();
        pub.setPublisherName("Pub pub publishing");
        pub.setState("Catamarca");

        Publisher pubSaved = publisherRepository.save(pub);

        /* ========= Authors (save first → need IDs) ========= */
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Pocholo");

        Author marcus = new Author();
        marcus.setFirstName("Marcus");
        marcus.setLastName("Aurelius");

        Author ericSaved = authorRepository.save(eric);
        Author marcusSaved = authorRepository.save(marcus);

        /* ========= Books (transient, no authors yet) ========= */
        Book turner = new Book();
        turner.setTitle("The Turner Diaries");
        turner.setIsbn("9780692943656");
        turner.setPublisher(pubSaved);

        Book meditations = new Book();
        meditations.setTitle("Marcus Aurelius Meditations");
        meditations.setIsbn("1441221124412");
        meditations.setPublisher(pubSaved);

        Book beExcellent = new Book();
        beExcellent.setTitle("Be Excellent");
        beExcellent.setIsbn("1234567890");
        beExcellent.setPublisher(pubSaved);

        /* ========= Relationship wiring (owning side only) ========= */
        turner.addAuthor(ericSaved);
        meditations.addAuthor(marcusSaved);
        beExcellent.addAuthor(marcusSaved);

        /* ========= Persist books ONCE ========= */
        bookRepository.save(turner);
        bookRepository.save(meditations);
        bookRepository.save(beExcellent);

        /* ========= Log ========= */
        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
