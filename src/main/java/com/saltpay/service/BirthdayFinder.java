package com.saltpay.service;

import com.saltpay.model.Person;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class BirthdayFinder {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final Logger logger = Logger.getLogger(BirthdayFinder.class.getName());
    private final Clock clock;

    public BirthdayFinder(Clock clock) {
        this.clock = clock;
    }

    /**
     * Find the person with the birthday.
     * @param people List of people
     * @return the people with the birthday
     */
    public List<String> process(final List<Person> people) {
        LocalDate today = LocalDate.now(clock);
        logger.info("Today is " + today.format(formatter));
        return people.parallelStream().filter(person -> this.isBirthdayToday(person, today)).map(Person::getName).toList();
    }

    /**
     * Check if the person's birthday is today.
     * @param person the person
     * @param today today's date
     * @return true if the person's birthday is today
     */
    private boolean isBirthdayToday(final Person person, final LocalDate today) {
        LocalDate dob = LocalDate.parse(person.getDob(), formatter);
        if (!today.isLeapYear() && dob.getMonthValue() == 2 && dob.getDayOfMonth() == 29 ) {
            dob = dob.minusDays(1);
        }
        //logger.info(person.getName() + " was born on " + dob.format(formatter));
        return (dob.getMonth() == today.getMonth() && dob.getDayOfMonth() == today.getDayOfMonth());
    }
}

