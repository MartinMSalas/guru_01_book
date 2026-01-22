package com.ingenieriasalas.spring_7_webapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String title;
    private String isbn;

    @ManyToMany
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "author_id"}))
    private Set<Author> authors = new HashSet<>();

    @ManyToOne
    Publisher publisher;


    /* ===== AUDITING ===== */

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant lastModifiedDate;

    /* ===== OPTIMISTIC LOCKING ===== */

    @Version
    private Long version;

    @Override
    public String toString() {
        return "Book{" +
                "createdDate=" + createdDate +
                ", id=" + id +
                ", isbn='" + isbn + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", title='" + title + '\'' +
                ", version=" + version +
                '}';
    }
}
