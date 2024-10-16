package org.example.factory;

import org.example.connection.ConnectionPool;
import org.example.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJDBCDAO<T, ID> implements GenericDAO<T, ID> {
    protected ConnectionPool connectionPool;

    public AbstractJDBCDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    protected abstract String getTableName();

    protected abstract String getIdColumnName();

    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    protected abstract void setEntityParameters(PreparedStatement ps, T entity) throws SQLException;

    @Override
    public T findById(ID id) throws DAOException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error finding entity by ID", e);
        }
        return null;
    }
    @Override
    public List<T> findAll() throws DAOException {
        String sql = "SELECT * FROM " + getTableName();
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (rs.next()) {
                entities.add(mapResultSetToEntity(rs));
            }
            return entities;
        } catch (SQLException e) {
            throw new DAOException("Error finding all entities", e);
        }
    }
    @Override
    public void save(T entity) throws DAOException {
        String sql = "INSERT INTO " + getTableName() + " VALUES (?)";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setEntityParameters(ps, entity);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error saving entity", e);
        }
    }

    @Override
    public void update(T entity) throws DAOException {
        String sql = "UPDATE " + getTableName() + " SET ? WHERE " + getIdColumnName() + " = ?";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setEntityParameters(ps, entity);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error updating entity", e);
        }
    }

    @Override
    public void delete(ID id) throws DAOException {
        String sql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + " = ?";
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error deleting entity", e);
        }
    }
}