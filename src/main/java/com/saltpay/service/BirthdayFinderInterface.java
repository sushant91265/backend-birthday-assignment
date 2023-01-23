package com.saltpay.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.saltpay.model.Person;

import java.util.List;

public interface BirthdayFinderInterface {
    public List<String> process(List<Person> people);

}
