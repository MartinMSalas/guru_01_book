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
public class Publisher {

    /* =========================
       IDENTIFIER
       ========================= */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* =========================
       BASIC FIELDS
       ========================= */
    private String publisherName;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    /* =========================
       RELATIONSHIPS
       ========================= */
    @OneToMany(mappedBy = "publisher")
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
     * Public method to attach a book to this publisher.
     * Delegates to owning side (Book.publisher).
     */
    public void addBook(Book book) {
        if (book == null) return;
        book.setPublisher(this); // owning side updates and calls internalAddBook
    }

    /**
     * Public method to detach a book from this publisher.
     */
    public void removeBook(Book book) {
        if (book == null) return;
        if (this.equals(book.getPublisher())) {
            book.setPublisher(null);
        }
    }

    /**
     * INTERNAL — called only from Book.setPublisher(...)
     */
    void internalAddBook(Book book) {
        books.add(book);
    }

    void internalRemoveBook(Book book) {
        books.remove(book);
    }

    /* =========================
       READ-ONLY ACCESSORS
       ========================= */

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    /* =========================
       GETTERS / SETTERS
       ========================= */

    public Long getId() {
        return id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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
        if (!(o instanceof Publisher other)) return false;
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
        return "Publisher{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
