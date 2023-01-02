package com.saltpay;

import java.io.IOException;
import java.time.Clock;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.saltpay.model.Person;
import com.saltpay.service.BirthdayFinder;
import com.saltpay.util.FileUtils;

public class App {
    private static final String DEFAULT_INPUT_FILE = "src/main/resources/input.json";

    /**
     * This is the main entry point for the application.
     */
    public static void main(String[] args) throws IOException {
        String inputJson = FileUtils.readFile(args.length>=1 ? args[0] : DEFAULT_INPUT_FILE);
        JsonNode root = FileUtils.parseString(inputJson, JsonNode.class);
        List<Person> people = FileUtils.parseJson(root, Person.class);

        List<String> birthdays = new BirthdayFinder(Clock.systemDefaultZone()).process(people);
        System.out.println(birthdays);
    }
}
