package com.library.library.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "series")
public class Series {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "planned_volumes")
    private Integer plannedVolumes;

    @Column(name = "book_tour_events")
    private Integer bookTourEvents;

    @ManyToMany(mappedBy = "series")
    private Set<Book> books = new HashSet<>();
}
