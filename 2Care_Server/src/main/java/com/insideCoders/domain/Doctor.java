package com.insideCoders.domain;

public class Doctor extends Entity<Integer> {
    private String firstName,lastName;

    public Doctor(Integer doctorId,String firstName, String lastName) {
        super.setId(doctorId);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
