package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookPostDto {

    @NotBlank
    @JsonProperty("id")
    private String id;

    @NotBlank
    @JsonProperty("title")
    private String title;

    @JsonProperty("volume")
    private Integer volume = 1;

    @JsonProperty("comment")
    private String comment = "";

    @NotEmpty
    @JsonProperty("genres")
    private Set<Integer> genres = new HashSet<>();

    @NotEmpty
    @JsonProperty("authors")
    private Set<String> authors = new HashSet<>();

    @NotEmpty
    @JsonProperty("series")
    private Set<String> series = new HashSet<>();
}
