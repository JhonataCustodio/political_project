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
public class AssociateDtoRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String politicalOffice;
    @NotNull
    private LocalDate birthday;
    @NotBlank
    private String sex;

}
