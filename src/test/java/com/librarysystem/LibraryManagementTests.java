package com.librarysystem;

import com.librarysystem.entities.Book;
import com.librarysystem.entities.Library;
import com.librarysystem.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryManagementTests {
    private Library library;
    private User user;
    private Book book1, book2;

    @BeforeEach
    void setUp() {
        resetLibraryInstance(); // Clear any previous test data
        library = Library.getInstance();
        user = new User("Alice");
        library.registerUser(user);

        book1 = new Book.BookBuilder("The Great Gatsby", "F. Scott Fitzgerald")
                .withISBN("978-3-16-148410-0")
                .withPublisher("Scribner")
                .withYearPublished(1925)
                .withGenre("Classic")
                .build();

        book2 = new Book.BookBuilder("1984", "George Orwell")
                .withISBN("978-0-452-28423-4")
                .build();

        library.addBook(book1);
        library.addBook(book2);
    }

    private void resetLibraryInstance() {
        try {
            java.lang.reflect.Field instance = Library.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to reset the Library singleton instance", e);
        }
    }

    @Test
    void testBookBuilderCreation() {
        assertEquals("The Great Gatsby", book1.getTitle());
        assertEquals("F. Scott Fitzgerald", book1.getAuthor());
    }

    @Test
    void testSingletonLibraryInstance() {
        Library anotherLibraryInstance = Library.getInstance();
        assertSame(library, anotherLibraryInstance);
    }

    @Test
    void testUserBorrowBook() {
        user.borrowBook(book1);
        assertTrue(book1.isBorrowed());
        assertTrue(user.getBorrowedBooks().contains(book1));
    }

    @Test
    void testUserReturnBook() {
        user.borrowBook(book1);
        user.returnBook(book1);
        assertFalse(book1.isBorrowed());
        assertFalse(user.getBorrowedBooks().contains(book1));
    }

    @Test
    void testFindBooksByAuthor() {
        var booksByOrwell = library.findBooksByAuthor("George Orwell");
        assertEquals(1, booksByOrwell.size());
        assertEquals("1984", booksByOrwell.get(0).getTitle());
    }

    @Test
    void testCannotBorrowAlreadyBorrowedBook() {
        user.borrowBook(book1);
        User anotherUser = new User("Bob");
        library.registerUser(anotherUser);

        assertThrows(IllegalStateException.class, () -> anotherUser.borrowBook(book1));
    }

    @Test
    void testRemovedBook() {
        user.borrowBook(book1);
        user.borrowBook(book2);
        System.out.println(user.getBorrowedBooks());

        user.returnBook(book1);
        System.out.println("empty:" + user.getBorrowedBooks());

    }
}
