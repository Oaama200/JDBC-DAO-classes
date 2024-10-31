import org.example.author.Author;
import org.example.author.AuthorEventNotifier;
import org.example.exception.ServiceException;
import org.example.services.AuthorService;
import org.example.services.AuthorServiceDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceDecoratorTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private AuthorEventNotifier eventNotifier;

    private AuthorServiceDecorator authorServiceDecorator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorServiceDecorator = new AuthorServiceDecorator(authorService, eventNotifier);
    }

    @Test
    void testGetAuthorById() throws ServiceException {
        Long authorId = 1L;
        Author expectedAuthor = new Author();
        when(authorService.getAuthorById(authorId)).thenReturn(expectedAuthor);

        Author result = authorServiceDecorator.getAuthorById(authorId);

        assertEquals(expectedAuthor, result);
        verify(authorService).getAuthorById(authorId);
    }

    @Test
    void testGetAllAuthors() throws ServiceException {
        List<Author> expectedAuthors = new ArrayList<>();
        when(authorService.getAllAuthors()).thenReturn(expectedAuthors);

        List<Author> result = authorServiceDecorator.getAllAuthors();

        assertEquals(expectedAuthors, result);
        verify(authorService).getAllAuthors();
    }

    @Test
    void testAddAuthor() throws ServiceException {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        authorServiceDecorator.addAuthor(author);

        verify(authorService).addAuthor(author);
        verify(eventNotifier).notifyAuthorAdded(author);
    }

    @Test
    void testAddInvalidAuthor() {
        Author invalidAuthor = new Author();
        assertThrows(ServiceException.class, () -> authorServiceDecorator.addAuthor(invalidAuthor));
    }

    @Test
    void testUpdateAuthor() throws ServiceException {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        authorServiceDecorator.updateAuthor(author);

        verify(authorService).updateAuthor(author);
        verify(eventNotifier).notifyAuthorUpdated(author);
    }

    @Test
    void testUpdateInvalidAuthor() {
        Author invalidAuthor = new Author();
        assertThrows(ServiceException.class, () -> authorServiceDecorator.updateAuthor(invalidAuthor));
    }

    @Test
    void testDeleteAuthor() throws ServiceException {
        Long authorId = 1L;

        authorServiceDecorator.deleteAuthor(authorId);

        verify(authorService).deleteAuthor(authorId);
        verify(eventNotifier).notifyAuthorDeleted(authorId);
    }

    @Test
    void testDeleteAuthorThrowsException() throws ServiceException {
        Long authorId = 1L;
        doThrow(new ServiceException("Author not found")).when(authorService).deleteAuthor(authorId);

        assertThrows(ServiceException.class, () -> authorServiceDecorator.deleteAuthor(authorId));
    }

    @Test
    void testIsValidAuthor() {
        Author validAuthor = new Author();
        validAuthor.setFirstName("John");
        validAuthor.setLastName("Doe");

        assertTrue(authorServiceDecorator.isValid(validAuthor));
    }

    @Test
    void testIsInvalidAuthor() {
        Author invalidAuthor = new Author();
        invalidAuthor.setFirstName(""); // Empty first name
        invalidAuthor.setLastName("Doe");

        assertFalse(authorServiceDecorator.isValid(invalidAuthor));
    }

    @Test
    void testPerformOperation() throws ServiceException {
        Runnable operation = mock(Runnable.class);
        authorServiceDecorator.performOperation("test", operation);

        verify(operation).run();
    }

    @Test
    void testPerformOperationThrowsException() {
        Runnable operation = mock(Runnable.class);
        doThrow(new RuntimeException("Test exception")).when(operation).run();

        assertThrows(ServiceException.class, () -> authorServiceDecorator.performOperation("test", operation));
    }
}