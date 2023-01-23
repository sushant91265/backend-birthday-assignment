package com.saltpay.service;

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
     * @param people the list of person objects
     * @return the list of birthday people
     */
    @Override
    public List<String> process(final List<Person> people) {
        List<String> birthdayPeople = new ArrayList<>();
        LocalDate today = LocalDate.now(clock);
        logger.info("Today is " + today.format(formatter));
        people.forEach(person -> {
            if (this.isBirthdayToday(person, today)) {
                birthdayPeople.add(person.getName());
            }
        });
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
            throw e;
        }
    }
}

