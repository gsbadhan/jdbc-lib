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
import com.jdbclib.connection.pool.C3P0Factory;

public class C3P0FactoryIT {
    private C3P0Factory factory;
    private String      dbConfigFile = "/Users/gurpreet.singh/git/jdbc-lib/src/test/resources/db-config.yaml";

    @Test
    public void up() throws FileNotFoundException, SQLException, JDBCException {
        Properties properties = YamlUtils.getProperties(dbConfigFile);
        assertNotNull(properties);
        factory = new C3P0Factory(properties);
        factory.init();
        assertNotNull(factory);
        Connection connection=factory.getConnection();
        assertNotNull(connection);
        boolean isClose=factory.releaseConnection(connection);
        assertTrue(isClose);
    }

    @After
    public void down() {
        if (factory != null)
            factory.destroy();
    }

}
