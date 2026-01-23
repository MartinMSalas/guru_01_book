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
public class Book {

    /* =========================
       IDENTIFIER
       ========================= */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* =========================
       BASIC FIELDS
       ========================= */
    private String title;
    private String isbn;

    /* =========================
       RELATIONSHIPS
       ========================= */

    // OWNING SIDE of Many-to-Many
    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "author_id"})
    )
    private Set<Author> authors = new HashSet<>();

    // OWNING SIDE of Many-to-One
    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;

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

    public void addAuthor(Author author) {
        if (author == null) return;
        this.authors.add(author);
        author.internalAddBook(this);
    }

    public void removeAuthor(Author author) {
        if (author == null) return;
        this.authors.remove(author);
        author.internalRemoveBook(this);
    }

    /**
     * Publisher relationship helpers (optional but recommended).
     * Keeps both sides consistent if you add internal methods on Publisher.
     */
    public void setPublisher(Publisher publisher) {
        // detach from previous publisher
        if (this.publisher != null) {
            this.publisher.internalRemoveBook(this);
        }
        this.publisher = publisher;

        // attach to new publisher
        if (publisher != null) {
            publisher.internalAddBook(this);
        }
    }

    /* =========================
       READ-ONLY ACCESSORS
       ========================= */

    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public Publisher getPublisher() {
        return publisher;
    }

    /* =========================
       GETTERS / SETTERS
       ========================= */

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
        if (!(o instanceof Book other)) return false;
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
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
