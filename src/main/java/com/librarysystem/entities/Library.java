
package com.librarysystem.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {
    private static Library instance;
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    private Library() {

    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book currentBook : books) {
            if (Objects.equals(currentBook.getTitle(), title)) {
                result.add(currentBook);
            }
        }
        return result;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book currentBook : books) {
            if (Objects.equals(currentBook.getAuthor(), author)) {
                result.add(currentBook);
            }
        }
        return result;
    }

    public void registerUser(User user) {
        users.add(user);
    }
}
