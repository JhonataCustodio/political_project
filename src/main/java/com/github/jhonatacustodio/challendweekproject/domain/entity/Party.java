package com.github.jhonatacustodio.challendweekproject.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "party")
@AllArgsConstructor
@NoArgsConstructor
public class Party {
    @Id
    private String idParty;
    @NotBlank
    private String partyName;
    @NotBlank
    private String acronym;
    @NotBlank
    private String ideology;
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate foundationDate;
    @OneToMany(mappedBy = "party")
    private List<Associate> associates;

    public Party(String idParty, String partyName, String acronym, String ideology, String foundationDate) {
    }
}
