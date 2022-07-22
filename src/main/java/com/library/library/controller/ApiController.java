package com.library.library.controller;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> index() {
        final HashMap<String, Object> res = new HashMap<>();
        final ArrayList<String> availableEndpoints = new ArrayList<String>();
        availableEndpoints.add("books");
        availableEndpoints.add("authors");
        availableEndpoints.add("series");
        availableEndpoints.add("genres");

        res.put("availableEndpoints", availableEndpoints);
        res.put("version", "1.0.0");
        res.put("name", "Gụọ API");

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
