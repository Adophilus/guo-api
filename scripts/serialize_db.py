import pandas as pd
from models import Session, Author, AuthorBook, Book, Genre, Series, Session

spreadsheets = pd.read_excel(
    "./bookshop.xlsx", sheet_name=["Book", "Author", "Info", "Series"]
)
spreadsheets["Info"]["Volume Number"] = (
    spreadsheets["Info"]["Volume Number"].fillna(1).astype(int)
)
spreadsheets["Info"]["Staff Comment"].fillna("", inplace=True)

with Session() as session:
    for _, row in spreadsheets["Info"].iterrows():
        bookBook = (
            spreadsheets["Book"]
            .loc[spreadsheets["Book"]["BookID"] == row["BookID1"] + str(row["BookID2"])]
            .iloc[0]
        )
        bookSeries = spreadsheets["Series"].loc[
            spreadsheets["Series"]["SeriesID"] == row["SeriesID"]
        ]
        bookSeries = bookSeries.iloc[0] if not (bookSeries.empty) else pd.DataFrame()
        bookAuthor = (
            spreadsheets["Author"]
            .loc[spreadsheets["Author"]["AuthID"] == bookBook["AuthID"]]
            .iloc[0]
        )
        volumeNumber = row["Volume Number"]

        if not (bookSeries.empty):
            series = (
                session.query(Series)
                .filter(Series.id == bookSeries["SeriesID"])
                .first()
            )
            if not (series):
                series = Series(
                    id=bookSeries["SeriesID"],
                    name=bookSeries["Series Name"],
                    planned_volumes=bookSeries["Planned Volumes"],
                    book_tour_events=bookSeries["Book Tour Events"],
                )
                session.add(series)

        genre = session.query(Genre).filter(Genre.name == row["Genre"]).first()
        if not (genre):
            genre = Genre(name=row["Genre"])
            session.add(genre)

        author = session.query(Author).filter(Author.id == bookAuthor["AuthID"]).first()
        if not (author):
            author = Author(
                id=bookAuthor["AuthID"],
                first_name=bookAuthor["First Name"],
                last_name=bookAuthor["Last Name"],
                birthday=bookAuthor["Birthday"],
                country=bookAuthor["Country of Residence"],
            )

        book = Book(
            id=bookBook["BookID"],
            title=bookBook["Title"],
            volume=volumeNumber,
            comment=row["Staff Comment"],
        )

        if not (bookSeries.empty):
            series.books.append(book)

        # author.books.append(book)
        book.authors.append(author)
        genre.books.append(book)

        session.add(author)
        session.add(book)

    session.commit()
