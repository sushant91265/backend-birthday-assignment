package com.saltpay.util;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saltpay.App;
import com.saltpay.model.Person;

public final class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class.getName());

    private FileUtils() {
    }

    public static JsonNode parseFile(final String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(Paths.get(fileName).toFile(), JsonNode.class);
    }

    public static List<Person> parseJson(final JsonNode root) {
        try {
            List<Person> people = new ArrayList<>();
            root.forEach(node -> {
                Person person = new Person(node.get(0).asText(), node.get(1).asText(), node.get(2).asText());
                people.add(person);
            });
            return people;
        } catch (RuntimeException e) {
            logger.severe("Error processing JSON: " + e);
            throw e;
        }
    }
}
