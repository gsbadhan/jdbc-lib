package com.jdbclib.connection.pool;

import java.util.Properties;

public class AbstractConnectionFactory {
    protected String driverClassName;
    protected String username;
    protected String password;
    protected String dbUrl;
    protected long   connectionTimeout;
    protected int initialPoolSize;
    protected int maxPoolSize;
    protected long maxIdleTime;

    protected AbstractConnectionFactory(Properties properties) {
        driverClassName = properties.getProperty("driverClassName");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        dbUrl = properties.getProperty("dbUrl");
        connectionTimeout = new Integer(properties.getProperty("connectionTimeout"));
    }
}
