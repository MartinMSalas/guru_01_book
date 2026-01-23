package com.ingenieriasalas.spring_7_webapp.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/*
 * Author: m
 * Date: 22/1/26
 * Project Name: guru-01-book
 * Description: beExcellent
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Publisher {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String publisherName;
    private String address;
    private String city;
    private String state;
    private String zipCode;


    @OneToMany(mappedBy = "publisher")
    private Set<Book> books = new HashSet<>();




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

}
