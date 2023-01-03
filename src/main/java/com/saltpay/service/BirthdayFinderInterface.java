package com.saltpay.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface BirthdayFinderInterface {
    public List<String> process(JsonNode root);

}
