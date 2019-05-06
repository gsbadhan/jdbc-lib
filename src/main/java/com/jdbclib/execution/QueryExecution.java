package com.jdbclib.execution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.jdbclib.connection.pool.JDBCException;

public class QueryExecution {
    private QueryExecution() {
    }

    public static <ST extends Statement> ResultSet executeQuery(ST statement) {
        try {
            if (statement instanceof PreparedStatement) {
                return ((PreparedStatement) statement).executeQuery();
            }
        } catch (Exception e) {
            throw new JDBCException(e);
        }
        return null;
    }

    public static <ST extends Statement> int executeUpdate(ST statement) {
        try {
            if (statement instanceof PreparedStatement) {
                return ((PreparedStatement) statement).executeUpdate();
            }
        } catch (Exception e) {
            throw new JDBCException(e);
        }
        return -1;
    }
}
