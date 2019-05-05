package com.jdbclib.execution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jdbclib.common.YamlUtils;
import com.jdbclib.connection.pool.C3P0Factory;
import com.jdbclib.connection.pool.PoolException;

public class QueryExecutionIT {

    private C3P0Factory factory;
    private String      dbConfigFile = "/Users/gurpreet.singh/git/jdbc-lib/src/test/resources/db-config.yaml";

    @Before
    public void up() throws FileNotFoundException, SQLException, PoolException {
        Properties properties = YamlUtils.getProperties(dbConfigFile);
        assertNotNull(properties);
        factory = new C3P0Factory(properties);
        factory.init();
        assertNotNull(factory);

    }

    @Test
    public void testPrepareStatement() throws SQLException {
        Connection connection = factory.getConnection();
        assertNotNull(connection);
        String sql = "insert into emp(name,dept,salary) values(:name, :dept, :salary)";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "gurpreet");
        params.put("dept", "gurpreet");
        params.put("salary", 122.67F);
        Statement statement = StatementFactory.prepareStatement(connection, sql, params);
        int status = QueryExecution.executeUpdate(statement);
        assertEquals(1, status);
        boolean isClose = factory.releaseConnection(connection);
        assertTrue(isClose);
    }

    @After
    public void down() {
        if (factory != null)
            factory.destroy();
    }
}
