package com.jdbclib.connection.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPFactory extends AbstractConnectionFactory implements ConnectionPool {
    public DBCPFactory(Properties properties) {
        super(properties);
    }

    private BasicDataSource ds;

    @Override
    public void init() throws PoolException {
        ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(dbUrl);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setInitialSize(initialPoolSize);
        ds.setMaxActive(maxPoolSize);
    }

    @Override
    public void destroy() throws SQLException {
        ds.close();

    }

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    @Override
    public boolean releaseConnection(Connection connection) throws SQLException {
        connection.close();
        return true;
    }

}
