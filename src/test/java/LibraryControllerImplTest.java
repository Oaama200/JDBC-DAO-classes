import org.example.author.Author;
import org.example.book.Book;
import org.example.exception.ServiceException;
import org.example.library.LibraryControllerImpl;
import org.example.library.LibraryFacade;
import org.example.library.LibraryView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class LibraryControllerImplTest {

    @Mock
    private LibraryFacade libraryFacade;
    @Mock
    private LibraryView libraryView;

    private LibraryControllerImpl libraryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        libraryController = new LibraryControllerImpl(libraryFacade, libraryView);
    }

    @Test
    void testAddBook() throws ServiceException {
        Book book = new Book();
        libraryController.addBook(book);
        verify(libraryFacade).addBook(book);
        verify(libraryView).displaySuccess(anyString());
    }

    @Test
    void testUpdateBook() throws ServiceException {
        Book book = new Book();
        libraryController.updateBook(book);
        verify(libraryFacade).updateBook(book);
        verify(libraryView).displaySuccess(anyString());
    }

    @Test
    void testDeleteBook() throws ServiceException {
        Long bookId = 1L;
        libraryController.deleteBook(bookId);
        verify(libraryFacade).deleteBook(bookId);
        verify(libraryView).displaySuccess(anyString());
    }

    @Test
    void testGetBookById() throws ServiceException {
        Long bookId = 1L;
        Book book = new Book();
        when(libraryFacade.getBookById(bookId)).thenReturn(book);
        libraryController.getBookById(bookId);
        verify(libraryFacade).getBookById(bookId);
        verify(libraryView).displayBook(book);
    }

    @Test
    void testGetAllBooks() throws ServiceException {
        List<Book> books = new ArrayList<>();
        when(libraryFacade.getAllBooks()).thenReturn(books);
        libraryController.getAllBooks();
        verify(libraryFacade).getAllBooks();
        verify(libraryView).displayBooks(books);
    }

    @Test
    void testAddAuthor() throws ServiceException {
        Author author = new Author();
        libraryController.addAuthor(author);
        verify(libraryFacade).addAuthor(author);
        verify(libraryView).displaySuccess(anyString());
    }

    @Test
    void testUpdateAuthor() throws ServiceException {
        Author author = new Author();
        libraryController.updateAuthor(author);
        verify(libraryFacade).updateAuthor(author);
        verify(libraryView).displaySuccess(anyString());
    }

    @Test
    void testDeleteAuthor() throws ServiceException {
        Long authorId = 1L;
        libraryController.deleteAuthor(authorId);
        verify(libraryFacade).deleteAuthor(authorId);
        verify(libraryView).displaySuccess(anyString());
    }

    @Test
    void testGetAuthorById() throws ServiceException {
        Long authorId = 1L;
        Author author = new Author();
        when(libraryFacade.getAuthorById(authorId)).thenReturn(author);
        libraryController.getAuthorById(authorId);
        verify(libraryFacade).getAuthorById(authorId);
        verify(libraryView).displayAuthor(author);
    }

    @Test
    void testGetAllAuthors() throws ServiceException {
        List<Author> authors = new ArrayList<>();
        when(libraryFacade.getAllAuthors()).thenReturn(authors);
        libraryController.getAllAuthors();
        verify(libraryFacade).getAllAuthors();
        verify(libraryView).displayAuthors(authors);
    }
}