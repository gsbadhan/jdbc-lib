package com.jdbclib.connection.pool;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.jdbclib.common.YamlUtils;

public class QueryModelLoader {
    private static final String       modelQueryMappingFileName = "model-query-mapping.yaml";
    public static Map<String, Object> modelQueryMapper          = Collections.emptyMap();

    private QueryModelLoader() {
    }

    public static void load(String modelQueryMappingFilePath) {
        String file = null;
        try {
            if (modelQueryMappingFilePath == null) {
                file = QueryModelLoader.class.getClass().getClassLoader().getSystemResource(modelQueryMappingFileName).getPath();
            } else {
                file = modelQueryMappingFilePath + File.separator + modelQueryMappingFileName;
            }
            Map<String, String> modelMapping = (Map<String, String>) YamlUtils.getMap(file);
            modelQueryMapper = new HashMap<>(modelMapping.size());
            modelMapping.forEach((modelName, filepath) -> {
                try {
                    modelQueryMapper.put(modelName, YamlUtils.getObject(modelName, filepath));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
