package com.jdbclib.execution;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecution {
    private QueryExecution() {
    }
    
    public static <ST extends Statement> ResultSet executeQuery(ST statement) throws SQLException {
        if(statement instanceof PreparedStatement) {
            return ((PreparedStatement) statement).executeQuery();
        }
        return null;
    }
    
    public static <ST extends Statement> int executeUpdate(ST statement) throws SQLException {
        if(statement instanceof PreparedStatement) {
            return ((PreparedStatement) statement).executeUpdate();
        }
        return -1;
    }
}
