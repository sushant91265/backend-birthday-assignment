package com.saltpay.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saltpay.model.Person;

public final class FileUtils {
    private FileUtils() {
    }

    public static <T> T parseString(final String inputJson, final Class<T> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(inputJson);
        return (T) root;
    }

    public static String readFile(final String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        return Files.readString(filePath);
    }
}
