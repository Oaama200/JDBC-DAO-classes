import org.example.author.Author;
import org.example.author.AuthorDAO;
import org.example.exception.DAOException;
import org.example.services.AuthorService;
import org.example.exception.ServiceException;
import org.example.services.AuthorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    private AuthorDAO authorDAO;
    private AuthorService authorService;

    @Before
    public void setUp() {
        authorDAO = Mockito.mock(AuthorDAO.class);
        authorService = new AuthorServiceImpl(authorDAO);
    }

    @Test
    public void testAddAuthor() throws ServiceException, DAOException {
        Author author = new Author();
        author.setFirstName("Moe");
        author.setLastName("ALA");

        authorService.addAuthor(author);

        verify(authorDAO, times(1)).save(author);
    }

    @Test
    public void testGetAuthorById() throws ServiceException, DAOException {
        Author author = new Author();
        author.setAuthorId(13L);
        author.setFirstName("Moe");

        when(authorDAO.findById(13L)).thenReturn(author);

        Author retrievedAuthor = authorService.getAuthorById(13L);

        assertNotNull("Author should not be null", retrievedAuthor);
        assertEquals("Moe", retrievedAuthor.getFirstName());
    }

    @Test
    public void testDeleteAuthor() throws ServiceException, DAOException {
        authorService.deleteAuthor(12L);

        verify(authorDAO, times(1)).delete(12L);
    }

    @Test
    public void testGetAllAuthors() throws ServiceException, DAOException {
        List<Author> authors = new ArrayList<>();

        when(authorDAO.findAll()).thenReturn(authors);

        List<Author> retrievedAuthors = authorService.getAllAuthors();

        assertNotNull(retrievedAuthors);
        assertEquals(authors.size(), retrievedAuthors.size());
    }
}