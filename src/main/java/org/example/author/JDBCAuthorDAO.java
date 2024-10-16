package org.example.author;
import org.example.factory.AbstractJDBCDAO;
import org.example.connection.ConnectionPool;
import org.example.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCAuthorDAO extends AbstractJDBCDAO<Author, Long> implements AuthorDAO {
    public JDBCAuthorDAO(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    @Override
    protected String getTableName() {
        return "author";
    }

    @Override
    protected String getIdColumnName() {
        return "author_id";
    }

    @Override
    protected Author mapResultSetToEntity(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setAuthorId(rs.getLong("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setBio(rs.getString("bio"));
        author.setBirthDate(rs.getDate("birth_date"));
        return author;
    }

    @Override
    protected void setEntityParameters(PreparedStatement ps, Author author) throws SQLException {
        ps.setString(1, author.getFirstName());
        ps.setString(2, author.getLastName());
        ps.setString(3, author.getBio());
        ps.setDate(4, new java.sql.Date(author.getBirthDate().getTime()));
    }

    @Override
    public void save(Author author) throws DAOException {
        String sql = "INSERT INTO " + getTableName() + " (first_name, last_name, bio, birth_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setEntityParameters(ps, author);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating author failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    author.setAuthorId(generatedKeys.getLong(1));
                } else {
                    throw new DAOException("Creating author failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error saving author", e);
        }
    }

    @Override
    public void update(Author author) throws DAOException {
        String sql = "UPDATE " + getTableName() + " SET first_name = ?, last_name = ?, bio = ?, birth_date = ? WHERE author_id = ?";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setEntityParameters(ps, author);
            ps.setLong(5, author.getAuthorId());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating author failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error updating author", e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        String sql = "DELETE FROM " + getTableName() + " WHERE author_id = ?";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting author failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error deleting author", e);
        }
    }
}