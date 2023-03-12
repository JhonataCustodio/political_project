package com.github.jhonatacustodio.challendweekproject.domain.enums;

public enum PoliticalOffice {
    ALDERMAN("Alderman"),
    MAYOR("Mayor"),
    STATE_DEPUTY("State Deputy"),
    FEDERAL_DEPUTY("Federal Deputy"),
    SENATOR("Senator"),
    GOVERNOR("Governor"),
    PRESIDENT("President"),
    NONE("None");
    private String politicalOffice;

    private PoliticalOffice(String politicalOffice) {
        this.politicalOffice = politicalOffice;
    }

    public String getPoliticalOffice() {
        return politicalOffice;
    }
}
