package org.example.book;

import org.example.factory.AbstractJDBCDAO;
import org.example.connection.ConnectionPool;
import org.example.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JDBCBookDAO extends AbstractJDBCDAO<Book, Long> implements BookDAO {
    public JDBCBookDAO(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected String getTableName() {
        return "book";
    }

    @Override
    protected String getIdColumnName() {
        return "book_id";
    }

    @Override
    protected Book mapResultSetToEntity(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getLong("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setPublicationYear(rs.getInt("publication_year"));
        book.setEditionNumber(rs.getInt("edition_number"));
        return book;
    }
    @Override
    protected void setEntityParameters(PreparedStatement ps, Book book) throws SQLException {
        ps.setString(1, book.getIsbn());
        ps.setString(2, book.getTitle());
        ps.setInt(3, book.getPublicationYear());
        ps.setInt(4, book.getEditionNumber());
    }

    @Override
    public List<Book> findAll() throws DAOException {
        String sql = "SELECT * FROM " + getTableName();
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                books.add(mapResultSetToEntity(rs));
            }
            return books;
        } catch (SQLException e) {
            throw new DAOException("Error finding all books", e);
        }
    }
    @Override
    public void save(Book book) throws DAOException {
        String sql = "INSERT INTO " + getTableName() + " (isbn, title, publication_year, edition_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setEntityParameters(ps, book);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating book failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setBookId(generatedKeys.getLong(1));
                } else {
                    throw new DAOException("Creating book failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error saving book", e);
        }
    }

    @Override
    public void update(Book book) throws DAOException {
        String sql = "UPDATE " + getTableName() + " SET isbn = ?, title = ?, publication_year = ?, edition_number = ? WHERE book_id = ?";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setEntityParameters(ps, book);
            ps.setLong(5, book.getBookId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating book failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error updating book", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        String sql = "DELETE FROM " + getTableName() + " WHERE book_id = ?";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting book failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error deleting book", e);
        }
    }
}