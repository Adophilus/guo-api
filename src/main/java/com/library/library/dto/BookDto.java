package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.library.dto.SeriesDto;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

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

    @JsonProperty("genres")
    private Set<String> genres = new HashSet<>();

    @JsonProperty("authors")
    private Set<AuthorSlimDto> authors = new HashSet<>();

    @JsonProperty("series")
    private Set<SeriesSlimDto> series = new HashSet<>();
}
