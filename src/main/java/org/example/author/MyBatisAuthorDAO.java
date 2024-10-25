package org.example.author;


import org.apache.ibatis.session.SqlSessionFactory;
import org.example.factory.AbstractMyBatisDAO;

public class MyBatisAuthorDAO extends AbstractMyBatisDAO<Author, Long> implements AuthorDAO {
    public MyBatisAuthorDAO(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }

    @Override
    protected String getNamespace() {
        return "mappers.AuthorMapper";
    }
}