package org.example;

import org.example.author.Author;
import org.example.author.AuthorDAO;
import org.example.book.BookDAO;
import org.example.book.Book;
import org.example.connection.ConnectionPool;
import org.example.factory.DAOFactory;
import org.example.factory.JDBCDAOFactory;
import org.example.services.BookService;
import org.example.services.BookServiceImpl;
import org.example.services.AuthorService;
import org.example.services.AuthorServiceImpl;

import java.sql.Date;
import java.util.List;

// This Main class is used for testing purposes only.
// It demonstrates how to set up and use the DAO and Service classes.
public class Main {
    public static void main(String[] args) {
        try {
            ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/librarysystem", "root", "root");
            DAOFactory daoFactory = new JDBCDAOFactory(connectionPool);

            BookDAO bookDAO = daoFactory.createBookDAO();
            AuthorDAO authorDAO = daoFactory.createAuthorDAO();
            AuthorService authorService = new AuthorServiceImpl(authorDAO );
            BookService bookService = new BookServiceImpl(bookDAO );
//            Uncomment the following lines to test various functionalities
//            Book newBook = new Book();
//            Author newAuthor = new Author();
//            newBook.setIsbn("123454367890");
//            newBook.setTitle("Sample Title");
//            newBook.setPublicationYear(2023);
//            newBook.setEditionNumber(1);
//            bookService.addBook(newBook);
//            Book retrievedBook = BookService.getBookById(9L);
//            if (retrievedBook != null) {
//                System.out.println("Retrieved Book: " + retrievedBook.getTitle());
//            } else {
//                System.out.println("No book found with ID: 1");
//            }
//
//            retrievedBook.setTitle("Updated Title");
//            BookService.updateBook(retrievedBook);
//
//            BookService.deleteBook(9L);
//
//            Date date = Date.valueOf("2020-10-30");
//            newAuthor.setBio("The GOAT");
//            newAuthor.setFirstName("Moe");
//            newAuthor.setLastName("ALA");
//            newAuthor.setBirthDate(date);
//            authorService.addAuthor(newAuthor);
//            Author retrievedAuthor = authorService.getAuthorById(11L);
//            if (retrievedAuthor != null) {
//                System.out.printf("Retrieved Author: %s, %s %n",retrievedAuthor.getFirstName(),retrievedAuthor.getLastName());
//            } else {
//                System.out.println("No Author found with ID: 1");
//            }
//            authorService.deleteAuthor(12L);
//            List<Author> authors = authorService.getAllAuthors();
//            for (Author author : authors) {
//                System.out.println(author);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}