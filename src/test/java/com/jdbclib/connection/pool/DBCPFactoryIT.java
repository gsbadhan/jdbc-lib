package com.jdbclib.connection.pool;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.After;
import org.junit.Test;

import com.jdbclib.common.YamlUtils;

public class DBCPFactoryIT {
    private DBCPFactory factory;
    private String      dbConfigFile = "/Users/gurpreet.singh/git/jdbc-lib/src/test/resources/db-config.yaml";

    @Test
    public void up() throws FileNotFoundException, SQLException, PoolException {
        Properties properties = YamlUtils.getProperties(dbConfigFile);
        assertNotNull(properties);
        factory = new DBCPFactory(properties);
        factory.init();
        assertNotNull(factory);
        Connection connection = factory.getConnection();
        assertNotNull(connection);
        boolean isClose = factory.releaseConnection(connection);
        assertTrue(isClose);
    }

    @After
    public void down() throws SQLException {
        if (factory != null)
            factory.destroy();
    }
}
