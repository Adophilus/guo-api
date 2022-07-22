package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesPutDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("plannedVolumes")
    private Integer plannedVolumes;

    @JsonProperty("bookTourEvents")
    private Integer bookTourEvents;

    @JsonProperty("books")
    private Set<String> books = new HashSet<>();
}
