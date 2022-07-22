package com.library.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Author does not exist!")
public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String id) {
        super(id);
    }
}
