package com.jdbclib.common;

import java.sql.ResultSet;

public interface RowMapper<T> {

    T mapRow(ResultSet rs, int rownum);
}
