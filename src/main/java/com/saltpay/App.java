package com.saltpay;

import java.io.IOException;
import java.time.Clock;
import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.saltpay.model.Person;
import com.saltpay.service.BirthdayFinder;
import com.saltpay.util.FileUtils;

public class App {
    private static final String DEFAULT_INPUT_FILE = "src/main/resources/input.json";
    private static final Logger logger = Logger.getLogger(App.class.getName());
    /**
     * This is the main entry point for the application.
     */
    public static void main(String[] args) throws IOException {
        JsonNode root = FileUtils.parseFile(args.length>=1 ? args[0] : DEFAULT_INPUT_FILE);
        List<Person> people = FileUtils.parseJson(root);

        BirthdayFinder birthdayFinder = new BirthdayFinder(Clock.systemDefaultZone());
        List<String> birthdays = birthdayFinder.process(people);
        logger.info(birthdays.toString());
    }
}
