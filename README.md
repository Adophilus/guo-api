Gụọ API
===

## About

Gụọ API enables users query data on genres, series, books and their authors through an intuitive API schema as defined by the [OpenAPI Specification v3](https://swagger.io/specification/). It virtually simulate the structure of a library

## Query Collections

- [Authors](#authors)
- [Books](#books)
- [Genres](#genres)
- [Series](#series)


### Author

#### Description
```typescript
type Author {
	id: string
	firstName: string
	lastName: string
	birthDay: Date // in the format yyyy-MM-dd
	country: string
	books: Book[]

}
```

#### Info

An `Author` represents an owner of a book. It represents the legal owner (s) of a piece of publication (`Book`).


### Book

#### Description
```typescript
type Book {
	id: string
	title: string
	volume: number
	comment: string 
	authors: Author[]
	series: Series[]
	genres: Genre[]
}
```

#### Info

A `Book` represents a single witten work by an `Author` or a group of `Authors`.


### Genre

#### Description
```typescript
type Genre {
	id: number
	name: string // unique
	books: Book[]
}
```

#### Info

A `Genre` represents the category a `Book` belongs to.


### Series

#### Description
```typescript
type Series {
	id: number
	name: string // unique
	plannedVolumes: number
	bookTourEvents: number
	books: Book[]
}
```

#### Info

A `Series` defines a sequence of `Books` in a particular order. It is genreally a set or sequence of related `Books`.


## Documentation

You can check out the API documentation here [guo-api.adophilus.xyz](https://guo-api.adophilus.xyz)


## Credits
- [Uchenna Ofoma](https://github.com/Adophilus) (@Adophilus)
