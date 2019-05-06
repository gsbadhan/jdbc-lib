package com.jdbclib.connection.pool;

import java.sql.Connection;

public interface ConnectionPool {
    void init();

    void destroy();

    Connection getConnection();

    boolean releaseConnection(Connection connection);
}
