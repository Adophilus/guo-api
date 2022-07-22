package com.library.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book does not exist!")
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String id) {
        super(id);
    }
}
