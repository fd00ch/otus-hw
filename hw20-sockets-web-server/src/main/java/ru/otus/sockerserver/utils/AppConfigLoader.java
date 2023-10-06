package ru.otus.sockerserver.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ru.otus.sockerserver.config.AppConfig;

import java.io.IOException;

public class AppConfigLoader {
    private static final String APPLICATION_FILE = "application.yaml";
    private final ObjectMapper ymlMapper = new ObjectMapper(new YAMLFactory());

    public AppConfig load() throws IOException {
        var applicationYamlFileStream = FileResourcesUtils.getFileFromResourceAsStream(APPLICATION_FILE);
        var settings = ymlMapper.readValue(applicationYamlFileStream, AppConfig.class);
        return settings;
    }
}
