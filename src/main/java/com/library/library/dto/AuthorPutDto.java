package com.library.library.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorPutDto {

    private String id;

    private String firstName;

    private String lastName;

    private Date birthDay;

    private String country;

    private Set<String> books = new HashSet<>();
}
