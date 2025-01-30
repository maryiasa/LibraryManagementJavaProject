package com.librarysystem.entities;

public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final String publisher;
    private final int yearPublished;
    private final String genre;
    private boolean isBorrowed;

    public Book(BookBuilder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.isbn = builder.isbn;
        this.publisher = builder.publisher;
        this.yearPublished = builder.yearPublished;
        this.genre = builder.genre;
    }

    public void borrow() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public static class BookBuilder {
        private String title;
        private String author;
        //optional fields
        private String isbn;
        private String publisher;
        private int yearPublished;
        private String genre;
        private boolean isBorrowed;

        public BookBuilder(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public BookBuilder withISBN(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder withPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookBuilder withYearPublished(int yearPublished) {
            this.yearPublished = yearPublished;
            return this;
        }

        public BookBuilder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearPublished=" + yearPublished +
                ", genre='" + genre + '\'' +
                '}';
    }
}
