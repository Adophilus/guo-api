package com.library.library.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class AuthorPostDto {

    @NotNull
    @JsonProperty("id")
    private String id;

    @NotNull
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @JsonProperty("lastName")
    private String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("birthDay")
    private Date birthDay;

    @NotNull
    @JsonProperty("country")
    private String country;

    @JsonProperty("books")
    private Set<String> books = new HashSet<>();
}
