package com.jdbclib.application.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.jdbclib.common.YamlUtils;
import com.jdbclib.connection.pool.C3P0Factory;
import com.jdbclib.connection.pool.ConnectionPool;
import com.jdbclib.connection.pool.JDBCException;
import com.jdbclib.connection.pool.QueryModelLoader;

public class EmpDaoImplTest {

    private ConnectionPool factory;
    private String         dbConfigFile = "/Users/gurpreet.singh/git/jdbc-lib/src/test/resources/db-config.yaml";
    private EmpDao         empDao;

    @Before
    public void up() throws FileNotFoundException, SQLException, JDBCException {
        Properties properties = YamlUtils.getProperties(dbConfigFile);
        assertNotNull(properties);
        factory = new C3P0Factory(properties);
        factory.init();
        assertNotNull(factory);
        //load queries from default resources
        QueryModelLoader.load(null);
        //
        empDao = new EmpDaoImpl(factory);
    }

    @Test
    public void testSave() {
        Connection connection = factory.getConnection();
        assertNotNull(connection);

        boolean status = empDao.save("test001", "dev", 234.90F);
        assertTrue(status);
        boolean isClose = factory.releaseConnection(connection);
        assertTrue(isClose);
    }
}
