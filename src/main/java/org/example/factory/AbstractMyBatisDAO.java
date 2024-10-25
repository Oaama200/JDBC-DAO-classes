package org.example.factory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.exception.DAOException;

import java.util.List;

public abstract class AbstractMyBatisDAO<T, ID> implements GenericDAO<T, ID> {
    protected SqlSessionFactory sqlSessionFactory;

    public AbstractMyBatisDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    protected abstract String getNamespace();

    @Override
    public T findById(ID id) throws DAOException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne(getNamespace() + ".findById", id);
        } catch (Exception e) {
            throw new DAOException("Error finding entity by ID", e);
        }
    }

    @Override
    public List<T> findAll() throws DAOException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList(getNamespace() + ".findAll");
        } catch (Exception e) {
            throw new DAOException("Error finding all entities", e);
        }
    }

    @Override
    public void save(T entity) throws DAOException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.insert(getNamespace() + ".insert", entity);
            sqlSession.commit();
        } catch (Exception e) {
            throw new DAOException("Error saving entity", e);
        }
    }

    @Override
    public void update(T entity) throws DAOException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.update(getNamespace() + ".update", entity);
            sqlSession.commit();
        } catch (Exception e) {
            throw new DAOException("Error updating entity", e);
        }
    }

    @Override
    public void delete(ID id) throws DAOException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            sqlSession.delete(getNamespace() + ".delete", id);
            sqlSession.commit();
        } catch (Exception e) {
            throw new DAOException("Error deleting entity", e);
        }
    }
}