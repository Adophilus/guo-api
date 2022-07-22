package com.library.library.mapper;

import com.library.library.dto.AuthorDto;
import com.library.library.dto.AuthorSlimDto;
import com.library.library.dto.BookDto;
import com.library.library.dto.BookPostDto;
import com.library.library.dto.BookSlimDto;
import com.library.library.dto.GenreDto;
import com.library.library.dto.GenreSlimDto;
import com.library.library.dto.SeriesDto;
import com.library.library.dto.SeriesSlimDto;
import com.library.library.entity.Author;
import com.library.library.entity.Book;
import com.library.library.entity.Genre;
import com.library.library.entity.Series;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.exception.BookNotFoundException;
import com.library.library.exception.GenreNotFoundException;
import com.library.library.exception.SeriesNotFoundException;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import com.library.library.repository.GenreRepository;
import com.library.library.repository.SeriesRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

//@Mapper(componentModel = "spring", uses = { IMapStructMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Mapper(componentModel = "spring")
public abstract class MapStructMapper {

    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    protected SeriesRepository seriesRepository;

    @Named("mapSeriesIdsToSeries")
    public Set<Series> mapSeriesIdsToSeries(Set<String> seriesIds) throws SeriesNotFoundException {
        Set<Series> series = new HashSet<>();
        if (seriesIds != null && !seriesIds.isEmpty()) {
            series =
                seriesIds
                    .stream()
                    .map(sid -> seriesRepository.findById(sid).orElseThrow(() -> new SeriesNotFoundException(sid)))
                    .collect(Collectors.toSet());
        }
        return series;
    }

    @Named("mapAuthorsToAuthorIds")
    public Set<String> mapAuthorsToAuthorIds(Set<Author> authors) {
        Set<String> authorIds = new HashSet<>();
        if (authors != null && !authors.isEmpty()) {
            authorIds = authors.stream().map(Author::getId).collect(Collectors.toSet());
        }
        return authorIds;
    }

    @Named("mapAuthorSlimDtosToAuthorIds")
    public Set<String> mapAuthorSlimDtosToAuthorIds(Set<AuthorSlimDto> authors) {
        Set<String> authorIds = new HashSet<>();
        if (authors != null && !authors.isEmpty()) {
            authorIds = authors.stream().map(AuthorSlimDto::getId).collect(Collectors.toSet());
        }
        return authorIds;
    }

    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void setSeriesRepository(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @Named("mapAuthorIdToAuthor")
    public Author mapAuthorIdToAuthor(String authorId) throws AuthorNotFoundException {
        return authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

    @Named("mapAuthorIdsToAuthors")
    public Set<Author> mapAuthorIdsToAuthors(Set<String> authorIds) throws AuthorNotFoundException {
        Set<Author> authors = new HashSet<>();
        if (authorIds != null && !authorIds.isEmpty()) {
            authors =
                authorIds
                    .stream()
                    .map(aid -> authorRepository.findById(aid).orElseThrow(() -> new AuthorNotFoundException(aid)))
                    .collect(Collectors.toSet());
        }
        return authors;
    }

    @Named("mapBooksToBookIds")
    public Set<String> mapBooksToBookIds(Set<Book> books) {
        Set<String> bookIds = new HashSet<>();
        if (books != null && !books.isEmpty()) {
            bookIds = books.stream().map(Book::getId).collect(Collectors.toSet());
        }
        return bookIds;
    }

    @Named("mapBookIdsToBooks")
    public Set<Book> mapBookIdsToBooks(Set<String> bookIds) throws BookNotFoundException {
        Set<Book> books = new HashSet<>();
        if (bookIds != null && !bookIds.isEmpty()) {
            books =
                bookIds
                    .stream()
                    .map(bid -> bookRepository.findById(bid).orElseThrow(() -> new BookNotFoundException(bid)))
                    .collect(Collectors.toSet());
        }
        return books;
    }

    @Named("mapGenresToGenreIds")
    public Set<Integer> mapGenresToGenreIds(Set<Genre> genres) {
        Set<Integer> genreIds = new HashSet<>();
        if (genres != null && !genres.isEmpty()) {
            genreIds = genres.stream().map(Genre::getId).collect(Collectors.toSet());
        }
        return genreIds;
    }

    @Named("mapGenreIdsToGenres")
    public Set<Genre> mapGenreIdsToGenres(Set<Integer> genreIds) throws GenreNotFoundException {
        Set<Genre> genres = new HashSet<>();
        if (genreIds != null && !genreIds.isEmpty()) {
            genres =
                genreIds
                    .stream()
                    .map(gn -> genreRepository.findById(gn).orElseThrow(() -> new GenreNotFoundException(gn)))
                    .collect(Collectors.toSet());
        }
        return genres;
    }

    @Named("mapGenresToGenreNames")
    public Set<String> mapGenresToGenreNames(Set<Genre> genres) {
        Set<String> genreNames = new HashSet<>();
        if (genres != null && !genres.isEmpty()) {
            genreNames = genres.stream().map(Genre::getName).collect(Collectors.toSet());
        }
        return genreNames;
    }

    @Named("mapGenreNameToGenre")
    public Genre mapGenreNameToGenre(String genreName) throws GenreNotFoundException {
        return genreRepository.findByName(genreName).orElseThrow(() -> new GenreNotFoundException(genreName));
    }

    @Named("mapGenreNamesToGenres")
    public Set<Genre> mapGenreNamesToGenres(Set<String> genreNames) throws GenreNotFoundException {
        Set<Genre> genres = new HashSet<>();
        if (genreNames != null && !genreNames.isEmpty()) {
            genres =
                genreNames
                    .stream()
                    .map(gn -> genreRepository.findByName(gn).orElseThrow(() -> new GenreNotFoundException(gn)))
                    .collect(Collectors.toSet());
        }
        return genres;
    }

    public Set<String> mapSeriesToSeriesIds(Set<Series> series) {
        Set<String> seriesIds = new HashSet<>();
        if (series != null && !series.isEmpty()) {
            seriesIds = series.stream().map(Series::getId).collect(Collectors.toSet());
        }
        return seriesIds;
    }

    public Set<String> mapSeriesSlimDtosToSeriesIds(Set<SeriesSlimDto> series) {
        Set<String> seriesIds = new HashSet<>();
        if (series != null && !series.isEmpty()) {
            seriesIds = series.stream().map(SeriesSlimDto::getId).collect(Collectors.toSet());
        }
        return seriesIds;
    }

    // Model --> DTO
    @Named("authorToAuthorDto")
    @Mapping(target = "books", expression = "java( booksToBookSlimDtos(author.getBooks()) )")
    public abstract AuthorDto authorToAuthorDto(Author author);

    @Named("bookToBookDto")
    @Mapping(target = "authors", expression = "java( authorsToAuthorSlimDtos(book.getAuthors()) )")
    @Mapping(target = "genres", expression = "java( mapGenresToGenreNames(book.getGenres()) )")
    @Mapping(target = "series", expression = "java( seriesToSeriesSlimDtos(book.getSeries()) )")
    public abstract BookDto bookToBookDto(Book book);

    @Named("genreToGenreDto")
    @Mapping(target = "books", expression = "java( mapBooksToBookIds(genre.getBooks()) )")
    public abstract GenreDto genreToGenreDto(Genre genre);

    @Named("seriesToSeriesDto")
    @Mapping(target = "books", expression = "java( booksToBookSlimDtos(series.getBooks()) )")
    public abstract SeriesDto seriesToSeriesDto(Series series);

    // DTO --> Model
    @Named("authorDtoToAuthor")
    @Mapping(target = "books", expression = "java( bookSlimDtosToBooks(authorDto.getBooks()) )")
    public abstract Author authorDtoToAuthor(AuthorDto authorDto);

    @Named("bookDtoToBook")
    @Mapping(target = "authors", expression = "java( authorSlimDtosToAuthors(bookDto.getAuthors()) )")
    @Mapping(target = "genres", expression = "java( mapGenreNamesToGenres(bookDto.getGenres()) )")
    @Mapping(target = "series", expression = "java( seriesSlimDtosToSeries(bookDto.getSeries()) )")
    public abstract Book bookDtoToBook(BookDto bookDto);

    @Named("genreDtoToGenre")
    @Mapping(target = "books", expression = "java( mapBookIdsToBooks(genreDto.getBooks()) )")
    public abstract Genre genreDtoToGenre(GenreDto genreDto);

    @Named("seriesDtoToSeries")
    @Mapping(target = "books", expression = "java( bookSlimDtosToBooks(seriesDto.getBooks()) )")
    public abstract Series seriesDtoToSeries(SeriesDto seriesDto);

    // Model --> DTO (slim)
    @Named("authorToAuthorSlimDto")
    @Mapping(target = "books", expression = "java( mapBooksToBookIds(author.getBooks()) )")
    public abstract AuthorSlimDto authorToAuthorSlimDto(Author author);

    @Named("bookToBookSlimDto")
    @Mapping(target = "series", expression = "java( mapSeriesToSeriesIds(book.getSeries()) )")
    @Mapping(target = "genres", expression = "java( mapGenresToGenreNames(book.getGenres()) )")
    @Mapping(target = "authors", expression = "java( mapAuthorsToAuthorIds(book.getAuthors()) )")
    public abstract BookSlimDto bookToBookSlimDto(Book book);

    @Named("genreToGenreSlimDto")
    public abstract GenreSlimDto genreToGenreSlimDto(Genre genre);

    @Named("seriesToSeriesSlimDto")
    @Mapping(target = "books", expression = "java( mapBooksToBookIds(series.getBooks()) )")
    public abstract SeriesSlimDto seriesToSeriesSlimDto(Series series);

    // DTO (slim) --> Model

    @Named("authorSlimDtoToAuthor")
    @Mapping(target = "books", expression = "java( mapBookIdsToBooks(author.getBooks()) )")
    public abstract Author authorSlimDtoToAuthor(AuthorSlimDto author);

    @Named("bookSlimDtoToBook")
    @Mapping(target = "authors", expression = "java( mapAuthorIdsToAuthors(book.getAuthors()) )")
    @Mapping(target = "genres", expression = "java( mapGenreNamesToGenres(book.getGenres()) )")
    @Mapping(target = "series", expression = "java( mapSeriesIdsToSeries(book.getSeries()) )")
    public abstract Book bookSlimDtoToBook(BookSlimDto book);

    @Named("seriesSlimDtoToSeries")
    @Mapping(target = "books", expression = "java( mapBookIdsToBooks(series.getBooks()) )")
    public abstract Series seriesSlimDtoToSeries(SeriesSlimDto series);

    // DTO (post) --> Model
    @Named("bookPostDtoToBook")
    @Mapping(target = "authors", expression = "java( mapAuthorIdsToAuthors(bookPostDto.getAuthors()) )")
    @Mapping(target = "genres", expression = "java( mapGenreIdsToGenres(bookPostDto.getGenres()) )")
    @Mapping(target = "series", expression = "java( mapSeriesIdsToSeries(bookPostDto.getSeries()) )")
    public abstract Book bookPostDtoToBook(BookPostDto bookPostDto);

    // Set<Model> --> Set<DTO>
    @IterableMapping(qualifiedByName = "authorToAuthorDto")
    public abstract Set<AuthorDto> authorsToAuthorDtos(Set<Author> authors);

    @IterableMapping(qualifiedByName = "bookToBookDto")
    public abstract Set<BookDto> booksToBookDtos(Set<Book> books);

    @IterableMapping(qualifiedByName = "genreToGenreDto")
    public abstract Set<GenreDto> genresToGenreDtos(Set<Genre> genres);

    @IterableMapping(qualifiedByName = "seriesToSeriesDto")
    public abstract Set<SeriesDto> seriesToSeriesDtos(Set<Series> series);

    // Set<Model> --> Set<DTO (slim)>
    @IterableMapping(qualifiedByName = "authorToAuthorSlimDto")
    public abstract Set<AuthorSlimDto> authorsToAuthorSlimDtos(Set<Author> authors);

    @IterableMapping(qualifiedByName = "bookToBookSlimDto")
    public abstract Set<BookSlimDto> booksToBookSlimDtos(Set<Book> books);

    @IterableMapping(qualifiedByName = "genreToGenreSlimDto")
    public abstract Set<GenreSlimDto> genresToGenreSlimDtos(Set<Genre> genre);

    @IterableMapping(qualifiedByName = "seriesToSeriesSlimDto")
    public abstract Set<SeriesSlimDto> seriesToSeriesSlimDtos(Set<Series> series);

    // Set<DTO (slim)> --> Set<model>
    @IterableMapping(qualifiedByName = "authorSlimDtoToAuthor")
    public abstract Set<Author> authorSlimDtosToAuthors(Set<AuthorSlimDto> authorSlimDtos);

    @IterableMapping(qualifiedByName = "bookSlimDtoToBook")
    public abstract Set<Book> bookSlimDtosToBooks(Set<BookSlimDto> bookSlimDtos);

    @IterableMapping(qualifiedByName = "seriesSlimDtoToSeries")
    public abstract Set<Series> seriesSlimDtosToSeries(Set<SeriesSlimDto> seriesSlimDtos);
}
