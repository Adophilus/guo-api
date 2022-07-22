package com.library.library.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenrePutDto {

    private String name;

    private Set<String> books = new HashSet<>();
}
