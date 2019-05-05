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
    public void init() throws PoolException {
        try {
            ds = new ComboPooledDataSource();
            ds.setDriverClass(driverClassName);
            ds.setJdbcUrl(dbUrl);
            ds.setUser(username);
            ds.setPassword(password);
            ds.setInitialPoolSize(initialPoolSize);
            ds.setMaxPoolSize(maxPoolSize);
            
        } catch (Exception e) {
            throw new PoolException(e);
        }

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

    @Override
    public void destroy() {
        ds.close();
    }

}
