package com.jdbclib.common;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jdbclib.connection.pool.JDBCException;

public class Rows {

    private Rows() {
    }

    public static <T> T single(ResultSet rs, RowMapper<T> rowMapper) {
        try {
            if (rs == null)
                return null;
            rs.next();
            return rowMapper.mapRow(rs, 1);
        } catch (Exception e) {
            throw new JDBCException(e);
        }
    }

    public static <T> List<T> list(ResultSet rs, RowMapper<T> rowMapper) {
        try {
            if (rs == null)
                return Collections.emptyList();
            int rownum = 1;
            List<T> list = new ArrayList<>(rs.getFetchSize());
            while (rs.next()) {
                T dt = rowMapper.mapRow(rs, rownum);
                list.add(dt);
                ++rownum;
            }
            return list;
        } catch (Exception e) {
            throw new JDBCException(e);
        }
    }
}
