package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSlimDto {

    @NotNull
    @JsonProperty("id")
    private String id;

    @NotNull
    @JsonProperty("title")
    private String title;

    @JsonProperty("volume")
    private Integer volume = 1;

    @JsonProperty("comment")
    private String comment = "";

    @JsonProperty("genre")
    private Set<String> genres = new HashSet<>();

    @JsonProperty("authors")
    private Set<String> authors = new HashSet<>();

    @JsonProperty("series")
    private Set<String> series = new HashSet<>();
}
