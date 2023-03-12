package com.github.jhonatacustodio.challendweekproject.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyDtoRequest {
    @NotBlank
    private String partyName;
    @NotBlank
    private String acronym;
    @NotBlank
    private String ideology;
    @NotNull
    private LocalDate foundationDate;
}
