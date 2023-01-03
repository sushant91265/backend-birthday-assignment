package com.saltpay.util;

import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public final class FileUtils {
    private FileUtils() {
    }

    public static JsonNode parseFile(final String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(Paths.get(fileName).toFile(), JsonNode.class);
    }
}
