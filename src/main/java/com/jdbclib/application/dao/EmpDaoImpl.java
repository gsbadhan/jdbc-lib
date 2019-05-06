package com.jdbclib.application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdbclib.application.pojo.model.Emp;
import com.jdbclib.application.pojo.query.EmpQuery;
import com.jdbclib.connection.pool.ConnectionPool;
import com.jdbclib.connection.pool.QueryModelLoader;
import com.jdbclib.execution.QueryExecution;
import com.jdbclib.execution.StatementFactory;

public class EmpDaoImpl implements EmpDao {
    private final ConnectionPool connectionPool;
    private EmpQuery             empQuery;

    public EmpDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        this.empQuery = (EmpQuery) QueryModelLoader.modelQueryMapper.get(EmpQuery.class.getCanonicalName());
    }

    @Override
    public boolean save(String name, String dept, float salary) {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Map<String, Object> params = new HashMap<>(3);
            params.put("name", name);
            params.put("dept", dept);
            params.put("salary", salary);
            String sql = empQuery.getInsert();
            int resultSet = QueryExecution.executeUpdate(StatementFactory.prepareStatement(connection, sql, params));
            return resultSet >= 1;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Emp> getAll() {
        return null;
    }

}
