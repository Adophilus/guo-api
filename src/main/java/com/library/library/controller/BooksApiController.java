package com.library.library.controller;

import com.library.library.dto.BookDto;
import com.library.library.dto.BookPostDto;
import com.library.library.dto.BookPutDto;
import com.library.library.dto.BookSlimDto;
import com.library.library.entity.Book;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.exception.BookNotFoundException;
import com.library.library.exception.SeriesNotFoundException;
import com.library.library.mapper.MapStructMapper;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
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
@RequestMapping("/api/books")
public class BooksApiController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MapStructMapper mapStructMapper;

    @PostMapping
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookPostDto bookPostDto) {
        return new ResponseEntity<>(
            mapStructMapper.bookToBookDto(bookRepository.save(mapStructMapper.bookPostDtoToBook(bookPostDto))),
            HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<BookSlimDto>> index() {
        return new ResponseEntity<>(
            new ArrayList<>(mapStructMapper.booksToBookSlimDtos(bookRepository.findAll().stream().collect(Collectors.toSet()))),
            HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    ResponseEntity<BookDto> show(@PathVariable("id") String id) {
        return new ResponseEntity<>(
            mapStructMapper.bookToBookDto(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id))),
            HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDto> update(@Valid @RequestBody BookPutDto bookDto, @PathVariable("id") String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (bookDto.getId() != null) {
            book.setId(bookDto.getId());
        }
        if (bookDto.getTitle() != null) {
            book.setTitle(bookDto.getTitle());
        }
        if (bookDto.getVolume() != null) {
            book.setVolume(bookDto.getVolume());
        }
        if (bookDto.getComment() != null) {
            book.setComment(bookDto.getComment());
        }
        if (bookDto.getGenres() != null) {
            book.setGenres(mapStructMapper.mapGenreIdsToGenres(bookDto.getGenres()));
        }
        if (bookDto.getAuthors() != null) {
            book.setAuthors(mapStructMapper.mapAuthorIdsToAuthors(bookDto.getAuthors()));
        }
        if (bookDto.getSeries() != null) {
            book.setSeries(mapStructMapper.mapSeriesIdsToSeries(bookDto.getSeries()));
        }
        return new ResponseEntity<>(mapStructMapper.bookToBookDto(bookRepository.save(book)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
        return new ResponseEntity<>(true, HttpStatus.GONE);
    }
}
