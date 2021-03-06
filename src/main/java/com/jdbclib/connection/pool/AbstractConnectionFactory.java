package com.jdbclib.connection.pool;

import java.util.Properties;

public class AbstractConnectionFactory {
    protected String  driverClassName;
    protected String  username;
    protected String  password;
    protected String  dbUrl;
    protected long    connectionTimeout;
    protected int     initialPoolSize;
    protected int     maxPoolSize;
    protected long    maxIdleTime;
    protected boolean autoCommit;

    protected AbstractConnectionFactory(Properties properties) {
        driverClassName = properties.getProperty("driverClassName");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        dbUrl = properties.getProperty("dbUrl");
        initialPoolSize = new Integer(properties.getProperty("initialPoolSize"));
        maxPoolSize = new Integer(properties.getProperty("maxPoolSize"));
        connectionTimeout = new Integer(properties.getProperty("connectionTimeout"));
        autoCommit = new Boolean(properties.getProperty("autoCommit"));
    }
}
