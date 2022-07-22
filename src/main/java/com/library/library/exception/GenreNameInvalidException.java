package com.library.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Genre name is invalid!")
public class GenreNameInvalidException extends RuntimeException {

    public GenreNameInvalidException(String name) {
        super(name);
    }
}
