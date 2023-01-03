package com.saltpay.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.saltpay.model.Person;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BirthdayFinder implements  BirthdayFinderInterface {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final Logger logger = Logger.getLogger(BirthdayFinder.class.getName());
    private final Clock clock;

    public BirthdayFinder(Clock clock) {
        this.clock = clock;
    }

    /**
     * Parse the JSON into a list of people.
     * @param root the root node of the JSON
     * @return the list of birthday people
     */
    @Override
    public List<String> process(final JsonNode root) {
        List<String> birthdayPeople = new ArrayList<>();
        try {
            LocalDate today = LocalDate.now(clock);
            logger.info("Today is " + today.format(formatter));

            root.forEach(node -> {
                Person person = new Person(node.get(0).asText(), node.get(1).asText(), node.get(2).asText());
                if (this.isBirthdayToday(person, today)) {
                    birthdayPeople.add(person.getName());
                }
            });
        } catch (RuntimeException e) {
            logger.severe("Error processing JSON: " + e);
            throw new RuntimeException(e);
        }
        return birthdayPeople;
    }

    /**
     * Check if the person's birthday is today.
     *
     * @param person the person
     * @param today  today's date
     * @return true if the person's birthday is today
     */
    private boolean isBirthdayToday(final Person person, final LocalDate today) {
        try {
            LocalDate dob = LocalDate.parse(person.getDob(), formatter);
            if (!today.isLeapYear() && dob.getMonthValue() == 2 && dob.getDayOfMonth() == 29) {
                dob = dob.minusDays(1);
            }
            return (dob.getMonth() == today.getMonth() && dob.getDayOfMonth() == today.getDayOfMonth());
        } catch (RuntimeException e) {
            logger.warning("Unable to parse date: " + e.getMessage());
            return false;
        }
    }
}

