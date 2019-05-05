package com.jdbclib.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.yaml.snakeyaml.Yaml;

public class YamlUtils {
    private YamlUtils() {
    }

    public static Properties getProperties(final String yamlFile) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        FileInputStream fileInputStream = new FileInputStream(new File(yamlFile));
        Properties properties = yaml.loadAs(fileInputStream, Properties.class);
        return properties;
    }
}
