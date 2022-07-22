package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.library.dto.BookSlimDto;
import com.library.library.entity.Book;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesPostDto {

    @NotNull
    @NotBlank
    @JsonProperty("id")
    private String id;

    @NotNull
    @NotBlank
    @JsonProperty("name")
    private String name;

    @JsonProperty("plannedVolumes")
    private Integer plannedVolumes = 1;

    @JsonProperty("bookTourEvents")
    private Integer bookTourEvents = 0;

    @JsonProperty("books")
    private Set<String> books = new HashSet<>();
}
