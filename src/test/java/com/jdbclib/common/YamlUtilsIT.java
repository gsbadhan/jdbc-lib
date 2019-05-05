package com.jdbclib.common;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.Properties;

import org.junit.Test;

import com.jdbclib.common.YamlUtils;

public class YamlUtilsIT {

    @Test
    public void testGetProperties() throws FileNotFoundException {
        String filepath="/Users/gurpreet.singh/git/jdbc-lib/src/test/resources/db-config.yaml";
        Properties properties=YamlUtils.getProperties(filepath);
        assertNotNull(properties);
    }
}
