package com.library.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Series does not exist!")
public class SeriesNotFoundException extends RuntimeException {

    public SeriesNotFoundException(String id) {
        super(id);
    }
}
