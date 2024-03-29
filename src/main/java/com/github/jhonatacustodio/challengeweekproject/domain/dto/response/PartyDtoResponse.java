package com.github.jhonatacustodio.challengeweekproject.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyDtoResponse {
    private String idParty;
    private String partyName;
    private String acronym;
    private String ideology;
    private LocalDate foundationDate;

    public PartyDtoResponse(String idParty, String partyName, String acronym, String ideology, String foundationDate) {
    }
}
