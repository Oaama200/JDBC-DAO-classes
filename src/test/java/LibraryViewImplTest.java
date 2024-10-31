import org.example.author.Author;
import org.example.book.Book;
import org.example.library.LibraryViewImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Year;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryViewImplTest {

    private LibraryViewImpl libraryView;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        libraryView = new LibraryViewImpl();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testDisplayBooks() {
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setIsbn("1234");
        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setIsbn("5678");
        List<Book> books = Arrays.asList(book1, book2);

        libraryView.displayBooks(books);

        String output = outContent.toString();
        assertTrue(output.contains("Books:"));
        assertTrue(output.contains("Book 1"));
        assertTrue(output.contains("Book 2"));
        assertTrue(output.contains("1234"));
        assertTrue(output.contains("5678"));
    }

    @Test
    void testDisplayAuthors() {
        Author author1 = new Author();
        author1.setFirstName("John");
        author1.setLastName("Doe");
        Author author2 = new Author();
        author2.setFirstName("Jane");
        author2.setLastName("Smith");
        List<Author> authors = Arrays.asList(author1, author2);

        libraryView.displayAuthors(authors);

        String output = outContent.toString();
        assertTrue(output.contains("Authors:"));
        assertTrue(output.contains("John Doe"));
        assertTrue(output.contains("Jane Smith"));
    }

    @Test
    void testDisplayBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("1234567890");
        book.setPublicationYear(Year.of(2023));
        book.setEditionNumber(1);

        libraryView.displayBook(book);

        String output = outContent.toString();
        assertTrue(output.contains("Book Details:"));
        assertTrue(output.contains("Test Book"));
        assertTrue(output.contains("1234567890"));
        assertTrue(output.contains("2023"));
        assertTrue(output.contains("1"));
    }

    @Test
    void testDisplayAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setBio("Test bio");
        author.setBirthDate(new Date());

        libraryView.displayAuthor(author);

        String output = outContent.toString();
        assertTrue(output.contains("Author Details:"));
        assertTrue(output.contains("John Doe"));
        assertTrue(output.contains("Test bio"));
    }

    @Test
    void testDisplayError() {
        libraryView.displayError("Test error");

        String output = outContent.toString();
        assertTrue(output.contains("Error: Test error"));
    }

    @Test
    void testDisplaySuccess() {
        libraryView.displaySuccess("Test success");

        String output = outContent.toString();
        assertTrue(output.contains("Success: Test success"));
    }
}