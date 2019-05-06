package com.jdbclib.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbclib.connection.pool.JDBCException;

public class LongRowMapper implements RowMapper<Long> {

    @Override
    public Long mapRow(ResultSet rs, int rownum) {
        try {
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new JDBCException(e);
        }
    }

}
