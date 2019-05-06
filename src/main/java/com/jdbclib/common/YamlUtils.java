package com.jdbclib.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

public class YamlUtils {
    private YamlUtils() {
    }

    public static Properties getProperties(final String yamlFile) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        FileInputStream fileInputStream = new FileInputStream(new File(yamlFile));
        Properties properties = yaml.loadAs(fileInputStream, Properties.class);
        IOUtils.closeQuietly(fileInputStream);
        return properties;
    }

    public static Map<String, ?> getMap(final String yamlFile) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        FileInputStream fileInputStream = new FileInputStream(new File(yamlFile));
        @SuppressWarnings("unchecked")
        Map<String, Object> map = yaml.loadAs(fileInputStream, LinkedHashMap.class);
        IOUtils.closeQuietly(fileInputStream);
        return map;
    }

    public static Object getObject(String fullClassName, String yamlFile) throws ClassNotFoundException, FileNotFoundException {
        FileInputStream fileInputStream = null;
        Yaml yaml = new Yaml();
        fileInputStream = new FileInputStream(new File(yamlFile));
        Object obj = yaml.loadAs(fileInputStream, Class.forName(fullClassName));
        IOUtils.closeQuietly(fileInputStream);
        return obj;
    }
}
