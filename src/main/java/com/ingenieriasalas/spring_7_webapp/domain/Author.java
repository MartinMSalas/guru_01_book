package com.ingenieriasalas.spring_7_webapp.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Author {

    /* =========================
       IDENTIFIER
       ========================= */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* =========================
       BASIC FIELDS
       ========================= */
    private String firstName;
    private String lastName;

    /* =========================
       RELATIONSHIPS
       ========================= */
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    /* =========================
       AUDITING
       ========================= */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant lastModifiedDate;

    /* =========================
       OPTIMISTIC LOCKING
       ========================= */
    @Version
    private Long version;

    /* =========================
       DOMAIN METHODS
       ========================= */

    /**
     * Public convenience method.
     * Delegates to owning side (Book).
     */
    public void addBook(Book book) {
        book.addAuthor(this);
    }

    public void removeBook(Book book) {
        book.removeAuthor(this);
    }

    /**
     * INTERNAL — used only by Book (owning side)
     */
    void internalAddBook(Book book) {
        books.add(book);
    }

    void internalRemoveBook(Book book) {
        books.remove(book);
    }

    /**
     * Read-only access to books
     */
    public Set<Book> getBooks() {
        //return Collections.unmodifiableSet(books);
        return Set.copyOf(books);
    }

    /* =========================
       GETTERS / SETTERS
       ========================= */
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    /* =========================
       EQUALITY (JPA-SAFE)
       ========================= */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    /* =========================
       DEBUG OUTPUT
       ========================= */
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
