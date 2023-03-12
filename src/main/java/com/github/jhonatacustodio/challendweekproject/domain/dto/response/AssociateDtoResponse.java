package com.github.jhonatacustodio.challendweekproject.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociateDtoResponse {
    private int idAssociate;
    private String fullName;
    private String politicalOffice;
    private LocalDate birthday;
    private String sex;
}
