package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookPutDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("volume")
    private Integer volume;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("genre")
    private Set<Integer> genres = new HashSet<>();

    @JsonProperty("authors")
    private Set<String> authors = new HashSet<>();

    @JsonProperty("series")
    private Set<String> series = new HashSet<>();
}
