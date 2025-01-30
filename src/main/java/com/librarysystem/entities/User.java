
package com.librarysystem.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Book> borrowedBooks = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void borrowBook(Book book) throws IllegalStateException {
        if (book.isBorrowed()) {
            throw new IllegalStateException("This book is currently unavailable");
        }

        book.borrow();
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) throws IllegalStateException {
        if (!book.isBorrowed()) {
            throw new IllegalStateException ("You don't have a book that should be returned");
        }

        book.returnBook();
        borrowedBooks.remove(book);
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
