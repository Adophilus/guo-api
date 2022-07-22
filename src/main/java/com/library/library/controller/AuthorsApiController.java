package com.library.library.controller;

import com.library.library.dto.AuthorDto;
import com.library.library.dto.AuthorPutDto;
import com.library.library.dto.AuthorSlimDto;
import com.library.library.entity.Author;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.mapper.MapStructMapper;
import com.library.library.repository.AuthorRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorsApiController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MapStructMapper mapStructMapper;

    @PostMapping
    public ResponseEntity<AuthorDto> create(@Valid @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(
            mapStructMapper.authorToAuthorDto(authorRepository.save(mapStructMapper.authorDtoToAuthor(authorDto))),
            HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<AuthorSlimDto>> index() {
        return new ResponseEntity<>(
            new ArrayList<>(mapStructMapper.authorsToAuthorSlimDtos(authorRepository.findAll().stream().collect(Collectors.toSet()))),
            HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    ResponseEntity<AuthorDto> show(@PathVariable("id") String id) {
        return new ResponseEntity<>(
            mapStructMapper.authorToAuthorDto(authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id))),
            HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<AuthorDto> update(@Valid @RequestBody AuthorPutDto authorDto, @PathVariable("id") String id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        if (authorDto.getFirstName() != null) {
            author.setFirstName(authorDto.getFirstName());
        }
        if (authorDto.getLastName() != null) {
            author.setLastName(authorDto.getLastName());
        }
        if (authorDto.getBirthDay() != null) {
            author.setBirthDay(authorDto.getBirthDay());
        }
        if (authorDto.getCountry() != null) {
            author.setCountry(authorDto.getCountry());
        }
        return new ResponseEntity<>(mapStructMapper.authorToAuthorDto(authorRepository.save(author)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        authorRepository.delete(author);
        return new ResponseEntity<>(true, HttpStatus.GONE);
    }
}
