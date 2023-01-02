package com.saltpay.model;

public class Person {
    private String lastName;
    private String firstName;
    private String dob;

    public Person(String lastName, String firstName, String dob) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dob = dob;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDob() {
        return dob;
    }

    /**
     * This method is used to print the Person object in a readable format.
     */
    public String getName() {
        return (firstName + " " + lastName);
    }

}
