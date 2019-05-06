package com.jdbclib.connection.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Factory extends AbstractConnectionFactory implements ConnectionPool {

    public C3P0Factory(Properties properties) {
        super(properties);
    }

    private ComboPooledDataSource ds;

    @Override
    public void init() {
        try {
            ds = new ComboPooledDataSource();
            ds.setDriverClass(driverClassName);
            ds.setJdbcUrl(dbUrl);
            ds.setUser(username);
            ds.setPassword(password);
            ds.setInitialPoolSize(initialPoolSize);
            ds.setMaxPoolSize(maxPoolSize);
            ds.setAutoCommitOnClose(autoCommit);

        } catch (Exception e) {
            throw new JDBCException(e);
        }

    }

    @Override
    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                throw new JDBCException(e);
            }
        return true;
    }

    @Override
    public void destroy() {
        ds.close();
    }

}
