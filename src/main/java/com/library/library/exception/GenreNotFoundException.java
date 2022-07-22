package com.library.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Genre does not exist!")
public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(Integer id) {
        super(id.toString());
    }

    public GenreNotFoundException(String name) {
        super(name);
    }
}
