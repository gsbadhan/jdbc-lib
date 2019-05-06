package com.jdbclib.application.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jdbclib.application.pojo.model.Emp;
import com.jdbclib.application.pojo.query.EmpQuery;
import com.jdbclib.common.RowMapper;
import com.jdbclib.common.Rows;
import com.jdbclib.connection.pool.ConnectionPool;
import com.jdbclib.connection.pool.JDBCException;
import com.jdbclib.connection.pool.QueryModelLoader;
import com.jdbclib.execution.QueryExecution;
import com.jdbclib.execution.StatementFactory;

public class EmpDaoImpl implements EmpDao {
    private final ConnectionPool  connectionPool;
    private EmpQuery              empQuery;
    private static RowMapper<Emp> rowMapper = new RowMapper<Emp>() {
                                                @Override
                                                public Emp mapRow(ResultSet rs, int rownum) {
                                                    Emp emp = new Emp();
                                                    try {
                                                        emp.setId(rs.getLong("id"));
                                                        emp.setName(rs.getString("name"));
                                                        emp.setDept(rs.getString("dept"));
                                                        emp.setSalary(rs.getFloat("salary"));
                                                    } catch (SQLException e) {
                                                        throw new JDBCException(e);
                                                    }
                                                    return emp;
                                                }
                                            };

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
            int result = QueryExecution.executeUpdate(StatementFactory.prepareStatement(connection, sql, params));
            return result >= 1;
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Emp> getAll() {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            String sql = empQuery.getAll();
            ResultSet resultSet = QueryExecution.executeQuery(StatementFactory.prepareStatement(connection, sql, Collections.emptyMap()));
            return  Rows.list(resultSet, rowMapper);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

}
