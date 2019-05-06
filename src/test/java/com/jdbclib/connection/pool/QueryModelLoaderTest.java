package com.jdbclib.connection.pool;

import org.junit.Test;

import com.jdbclib.connection.pool.QueryModelLoader;

public class QueryModelLoaderTest {

    @Test
    public void testLoad() {
        QueryModelLoader.load("/Users/gurpreet.singh/git/jdbc-lib/src/main/resources");
    }
}
