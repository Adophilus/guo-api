package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesSlimDto {

    @NotNull
    @JsonProperty("id")
    private String id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("plannedVolumes")
    private Integer plannedVolumes = 1;

    @JsonProperty("bookTourEvents")
    private Integer bookTourEvents = 0;

    @JsonProperty("books")
    private Set<String> books = new HashSet<>();
}
