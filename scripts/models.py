from sqlalchemy import (
    create_engine,
    MetaData,
    Table,
    String,
    Integer,
    Column,
    Date,
    ForeignKey,
    DateTime,
    Text,
)
from sqlalchemy.orm import mapper, relationship, sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from dotenv import load_dotenv
import os

load_dotenv()

engine = create_engine(
    f"mysql+pymysql://{os.getenv('DB_USERNAME')}:{os.getenv('DB_PASSWORD')}@{os.getenv('DB_HOST')}/{os.getenv('DB_DATABASE')}?host={os.getenv('DB_HOST')}?port={os.getenv('DB_PORT')}",
    connect_args={"host": os.getenv("DB_HOST"), "port": int(os.getenv("DB_PORT"))},
)
Session = sessionmaker(engine)

Base = declarative_base()


class AuthorBook(object):
    def __init__(self, author, book):
        self.author = author
        self.book = book


author_book = Table(
    "author_book",
    Base.metadata,
    Column("id", Integer, primary_key=True),
    Column("author", ForeignKey("authors.id")),
    Column("book", ForeignKey("books.id")),
)

mapper(AuthorBook, author_book)


class BookGenre(object):
    def __init__(self, book, genre):
        self.book = book
        self.genre = genre


book_genre = Table(
    "book_genre",
    Base.metadata,
    Column("id", Integer, primary_key=True),
    Column("book", ForeignKey("books.id")),
    Column("genre", ForeignKey("genres.id")),
)

mapper(BookGenre, book_genre)


class BookSeries(object):
    def __init__(self, book, series):
        self.book = book
        self.series = series


book_series = Table(
    "book_series",
    Base.metadata,
    Column("id", Integer, primary_key=True),
    Column("book", ForeignKey("books.id")),
    Column("series", ForeignKey("series.id")),
)

mapper(BookSeries, book_series)


class Author(Base):
    __tablename__ = "authors"

    id = Column(String(5), primary_key=True)
    first_name = Column(String(50), nullable=False)
    last_name = Column(String(50), nullable=False)
    birthday = Column(Date, nullable=False)
    books = relationship("Book", secondary=author_book, back_populates="authors")
    country = Column(String(50), nullable=False)


class Series(Base):
    __tablename__ = "series"

    id = Column(String(6), primary_key=True)
    name = Column(String(50), nullable=False)
    planned_volumes = Column(Integer, nullable=False)
    book_tour_events = Column(Integer, nullable=False)
    books = relationship("Book", secondary=book_series, back_populates="series")


class Genre(Base):
    __tablename__ = "genres"

    id = Column(Integer, primary_key=True)
    name = Column(String(50), nullable=False, unique=True)
    books = relationship("Book", secondary=book_genre, back_populates="genre")


class Book(Base):
    __tablename__ = "books"

    id = Column(String(5), primary_key=True)
    title = Column(String(100), nullable=False)
    volume = Column(Integer, nullable=False)
    comment = Column(Text, nullable=False, default="")
    authors = relationship("Author", secondary=author_book, back_populates="books")
    genre = relationship("Genre", secondary=book_genre, back_populates="books")
    series = relationship("Series", secondary=book_series, back_populates="books")


Base.metadata.drop_all(engine)
Base.metadata.create_all(engine)
