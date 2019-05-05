package com.jdbclib.connection.pool;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    void init() throws PoolException;

    void destroy() throws SQLException;

    Connection getConnection() throws SQLException;

    boolean releaseConnection(Connection connection) throws SQLException;
}
