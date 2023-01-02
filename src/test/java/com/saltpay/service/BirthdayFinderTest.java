package com.saltpay.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.saltpay.model.Person;
import com.saltpay.util.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

public class BirthdayFinderTest {
    private static final String RESOURCE = "src/test/resources/input-test.json";
    private static List<Person> people;
    @Before
    public void setUp() throws IOException {
        String inputJson = FileUtils.readFile(RESOURCE);
        JsonNode root = FileUtils.parseString(inputJson, JsonNode.class);
        people = FileUtils.parseJson(root, Person.class);
    }

    @Test
    public void testProcessForSameDayAndMonthAndYearBirthday_1() {
        Clock clock = Clock.fixed(Instant.parse("1982-08-08T00:00:00.00Z"), ZoneId.of("UTC"));
        List<String> birthdays = new BirthdayFinder(clock).process(people);

        Assert.assertEquals(1, birthdays.size());
        Assert.assertEquals("John Boris", birthdays.get(0));
    }

    @Test
    public void testProcessForSameDayAndMonthButNotSameYearBirthday_2() {
        Clock clock = Clock.fixed(Instant.parse("1989-08-08T00:00:00.00Z"), ZoneId.of("UTC"));
        List<String> birthdays = new BirthdayFinder(clock).process(people);

        Assert.assertEquals(1, birthdays.size());
        Assert.assertEquals("John Boris", birthdays.get(0));
    }

    @Test
    public void testProcessForMultipleBirthDays_3() {
        Clock clock = Clock.fixed(Instant.parse("2023-01-02T00:00:00.00Z"), ZoneId.of("UTC"));
        List<String> birthdays = new BirthdayFinder(clock).process(people);

        Assert.assertEquals(3, birthdays.size());
        Assert.assertEquals("Mark Curry", birthdays.get(0));
        Assert.assertEquals("Mike Curry", birthdays.get(1));
        Assert.assertEquals("Mark Curry", birthdays.get(2));
    }

    @Test
    public void testProcessForLeapYearBirthDay_4() {
        Clock clock = Clock.fixed(Instant.parse("2024-02-29T00:00:00.00Z"), ZoneId.of("UTC"));
        List<String> birthdays = new BirthdayFinder(clock).process(people);

        Assert.assertEquals(1, birthdays.size());
        Assert.assertEquals("Lady Mama", birthdays.get(0));
    }

    @Test
    public void testProcessForNonLeapYearBirthDay_5() {
        Clock clock = Clock.fixed(Instant.parse("2025-02-28T00:00:00.00Z"), ZoneId.of("UTC"));
        List<String> birthdays = new BirthdayFinder(clock).process(people);

        Assert.assertEquals(2, birthdays.size());
        Assert.assertEquals("Lady Mama", birthdays.get(0));
        Assert.assertEquals("Lady Kaka", birthdays.get(1));
    }

    @Test
    public void testProcessForNoBirthDay_6() {
        Clock clock = Clock.fixed(Instant.parse("2025-11-28T00:00:00.00Z"), ZoneId.of("UTC"));
        List<String> birthdays = new BirthdayFinder(clock).process(people);

        Assert.assertEquals(0, birthdays.size());
    }
}
