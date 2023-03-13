package com.github.jhonatacustodio.challengeweekproject.domain.enums;

public enum Ideology {
    CENTER("Center"),
    LEFT("Left"),
    RIGHT("Right");

    private String ideology;
    private Ideology(String ideology) {
        this.ideology = ideology;
    }

    public String getIdeology() {
        return ideology;
    }

}
