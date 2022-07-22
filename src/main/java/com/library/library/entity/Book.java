package com.library.library.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    private String id;

    @Column(unique = true)
    private String title;

    private Integer volume = 1;

    private String comment = "";

    @ManyToMany
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book"), inverseJoinColumns = @JoinColumn(name = "genre"))
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book"), inverseJoinColumns = @JoinColumn(name = "author"))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "book_series", joinColumns = @JoinColumn(name = "book"), inverseJoinColumns = @JoinColumn(name = "series"))
    private Set<Series> series = new HashSet<>();
}
