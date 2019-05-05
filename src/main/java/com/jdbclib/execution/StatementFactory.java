package com.jdbclib.execution;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.httprpc.sql.Parameters;

public class StatementFactory {
    private StatementFactory() {
    }

    public static Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    public static PreparedStatement prepareStatement(Connection connection, String sql, Map<String, ?> params) throws SQLException {
        Parameters parameters = Parameters.parse(sql);
        PreparedStatement statement = connection.prepareStatement(parameters.getSQL());
        parameters.apply(statement, params);
        return statement;
    }

    public static CallableStatement prepareCall(Connection connection, String sql, Map<String, ?> params) throws SQLException {
        Parameters parameters = Parameters.parse(sql);
        CallableStatement statement= connection.prepareCall(sql);
        parameters.apply(statement, params);
        return statement;
    }

    public void close(Statement statement) throws SQLException {
        statement.close();
    }
}
