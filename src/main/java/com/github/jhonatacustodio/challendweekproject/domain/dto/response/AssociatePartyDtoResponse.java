package com.github.jhonatacustodio.challendweekproject.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociatePartyDtoResponse {
    private int id;
    private int idAssociate;
    private String idParty;
}
