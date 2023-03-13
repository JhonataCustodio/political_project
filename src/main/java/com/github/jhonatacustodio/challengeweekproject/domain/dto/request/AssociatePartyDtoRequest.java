package com.github.jhonatacustodio.challengeweekproject.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociatePartyDtoRequest {
    @NotNull
    @Min(value = 1)
    private int idAssociate;
    @NotBlank
    private String idParty;
}
