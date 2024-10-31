import org.example.book.Book;
import org.example.exception.ServiceException;
import org.example.services.BookService;
import org.example.services.BookServiceDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceDecoratorTest {

    @Mock
    private BookService bookService;

    private BookServiceDecorator bookServiceDecorator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookServiceDecorator = new BookServiceDecorator(bookService);
    }

    @Test
    void testGetBookById() throws ServiceException {
        Long bookId = 1L;
        Book expectedBook = new Book();
        when(bookService.getBookById(bookId)).thenReturn(expectedBook);

        Book result = bookServiceDecorator.getBookById(bookId);

        assertEquals(expectedBook, result);
        verify(bookService).getBookById(bookId);
    }

    @Test
    void testGetAllBooks() throws ServiceException {
        List<Book> expectedBooks = new ArrayList<>();
        when(bookService.getAllBooks()).thenReturn(expectedBooks);

        List<Book> result = bookServiceDecorator.getAllBooks();

        assertEquals(expectedBooks, result);
        verify(bookService).getAllBooks();
    }

    @Test
    void testAddBook() throws ServiceException {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("1234567890");

        bookServiceDecorator.addBook(book);

        verify(bookService).addBook(book);
    }

    @Test
    void testAddInvalidBook() {
        Book invalidBook = new Book();
        assertThrows(ServiceException.class, () -> bookServiceDecorator.addBook(invalidBook));
    }

    @Test
    void testUpdateBook() throws ServiceException {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("1234567890");

        bookServiceDecorator.updateBook(book);

        verify(bookService).updateBook(book);
    }

    @Test
    void testUpdateInvalidBook() {
        Book invalidBook = new Book();
        assertThrows(ServiceException.class, () -> bookServiceDecorator.updateBook(invalidBook));
    }

    @Test
    void testDeleteBook() throws ServiceException {
        Long bookId = 1L;

        bookServiceDecorator.deleteBook(bookId);

        verify(bookService).deleteBook(bookId);
    }
}