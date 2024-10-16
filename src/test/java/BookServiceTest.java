import org.example.book.Book;
import org.example.book.BookDAO;
import org.example.exception.DAOException;
import org.example.exception.ServiceException;
import org.example.services.BookService;
import org.example.services.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    private BookDAO bookDAO;
    private BookService bookService;

    @Before
    public void setUp() {
        bookDAO = Mockito.mock(BookDAO.class);
        bookService = new BookServiceImpl(bookDAO);
    }

    @Test
    public void testAddBook() throws ServiceException, DAOException {
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Sample Title");

        bookService.addBook(book);

        verify(bookDAO, times(1)).save(book);
    }

    @Test
    public void testGetBookById() throws ServiceException, DAOException {
        Book book = new Book();
        book.setBookId(11L);
        book.setTitle("Sample Title");

        when(bookDAO.findById(11L)).thenReturn(book);

        Book retrievedBook = bookService.getBookById(11L);

        assertNotNull("Book should not be null", retrievedBook);
        assertEquals("Sample Title", retrievedBook.getTitle());
    }
    @Test
    public void testDeleteBook() throws ServiceException, DAOException {
        bookService.deleteBook(9L);

        verify(bookDAO, times(1)).delete(9L);
    }
}