package com.library.library.controller;

import com.library.library.dto.GenreDto;
import com.library.library.dto.GenrePutDto;
import com.library.library.dto.GenreSlimDto;
import com.library.library.entity.Genre;
import com.library.library.exception.GenreNameInvalidException;
import com.library.library.exception.GenreNotFoundException;
import com.library.library.mapper.MapStructMapper;
import com.library.library.repository.GenreRepository;
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
@RequestMapping("/api/genres")
public class GenresApiController {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MapStructMapper mapStructMapper;

    @PostMapping
    public ResponseEntity<GenreDto> create(@Valid @RequestBody GenrePutDto genreDto) {
        Genre genre = new Genre();
        if (genreDto.getName() == null) {
            throw new GenreNameInvalidException(genreDto.getName());
        }
        genre.setName(genreDto.getName());
        return new ResponseEntity<>(mapStructMapper.genreToGenreDto(genreRepository.save(genre)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreSlimDto>> index() {
        return new ResponseEntity<>(
            new ArrayList<>(mapStructMapper.genresToGenreSlimDtos(genreRepository.findAll().stream().collect(Collectors.toSet()))),
            HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    ResponseEntity<GenreDto> show(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(
            mapStructMapper.genreToGenreDto(genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id))),
            HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<GenreDto> update(@Valid @RequestBody GenrePutDto genreDto, @PathVariable("id") Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        if (genreDto.getName() != null) {
            genre.setName(genreDto.getName());
        }
        if (!genreDto.getBooks().isEmpty()) {
            genre.setBooks(mapStructMapper.mapBookIdsToBooks(genreDto.getBooks()));
        }
        return new ResponseEntity<>(mapStructMapper.genreToGenreDto(genreRepository.save(genre)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
        genreRepository.delete(genre);
        return new ResponseEntity<>(true, HttpStatus.GONE);
    }
}
