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
    public void init()  {
        ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(dbUrl);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setInitialSize(initialPoolSize);
        ds.setMaxActive(maxPoolSize);
        ds.setDefaultAutoCommit(autoCommit);
    }

    @Override
    public void destroy()  {
        try {
            ds.close();
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public Connection getConnection()  {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

    @Override
    public boolean releaseConnection(Connection connection)  {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                throw new JDBCException(e);
            }
        return true;
    }

}
