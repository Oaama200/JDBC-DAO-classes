package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {
    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 100;
    private static final int MAX_TIMEOUT = 5000;

    private final BlockingQueue<Connection> connectionPool;
    private final List<Connection> usedConnections = new ArrayList<>();

    public ConnectionPool(String url, String user, String password) throws SQLException {
        connectionPool = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection(url, user, password));
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            Connection connection = connectionPool.poll(MAX_TIMEOUT, TimeUnit.MILLISECONDS);
            if (connection == null) {
                throw new SQLException("Connection timeout");
            }
            usedConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SQLException("Error getting connection from pool", e);
        }
    }

    private static Connection createConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}