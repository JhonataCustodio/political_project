package com.github.jhonatacustodio.challengeweekproject.domain.enums;

public enum Sex {
    MALE("Male"),
    FEMALE("Female");

    private String sex;
    private Sex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
