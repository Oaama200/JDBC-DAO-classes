package org.example;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.type.YearTypeHandler;
import org.example.author.Author;
import org.example.author.AuthorAdditionStrategy;
import org.example.author.AuthorDAO;
import org.example.author.DefaultAuthorAdditionStrategy;
import org.example.book.Book;
import org.example.book.BookDAO;
import org.example.factory.DAOFactory;
import org.example.services.*;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Load MyBatis configuration file
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // Initialize DAO factories
            DAOFactory daoFactory = new MyBatisDAOFactory(sqlSessionFactory);
            BookDAO bookDAO = daoFactory.createBookDAO();
            AuthorDAO authorDAO = daoFactory.createAuthorDAO();

            AuthorAdditionStrategy authorAdditionStrategy = new DefaultAuthorAdditionStrategy(authorDAO);
            // Initialize services
            AuthorService authorService = new MyBatisAuthorService(authorDAO, authorAdditionStrategy);
            BookService bookService = new MyBatisBookService(bookDAO);


            // Add a new author
//            Author newAuthor = new Author();
//            newAuthor.setAuthorId(3L);
//            newAuthor.setFirstName("John");
//            newAuthor.setLastName("Doe");
//            authorService.addAuthor(newAuthor);
//            System.out.println("Added author: " + newAuthor);
            // Get author by ID
//            Author author = authorService.getAuthorById(1L);
//            System.out.println("Retrieved author: " + author);
//            // Update author
//            author.setLastName("Smith");
//            authorService.updateAuthor(author);
//            System.out.println("Updated author: " + author);
            // Get all authors
//            List<Author> authors = authorService.getAllAuthors();
//            System.out.println("All authors: " + authors);


            // Delete an author
//            authorService.deleteAuthor(author.getAuthorId());
//            System.out.println("Deleted author with ID: " + author.getAuthorId());

            // Add a new book
//            Book newBook = new Book();
//            newBook.setBookId(2L);
//            newBook.setTitle("Sample Book2");
//            newBook.setIsbn("12345678902");
//            Year year = Year.parse("2025");
//            newBook.setPublicationYear(year);
//
//            bookService.addBook(newBook);
//            System.out.println("Added book: " + newBook);
//
//            // Get book by ID
//            Book book = bookService.getBookById(2L);
//            System.out.println("Retrieved book: " + book);
//
//            // Update book
//            book.setTitle("Updated Title");
//            bookService.updateBook(book);
//            System.out.println("Updated book: " + book);
//
//            // Get all books
//            List<Book> books = bookService.getAllBooks();
//            System.out.println("All books: " + books);
//            // Delete a book
//            bookService.deleteBook(1L);
//            System.out.println("Deleted book with ID: " + book.getBookId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
