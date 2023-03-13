package com.github.jhonatacustodio.challengeweekproject.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "associate")
@AllArgsConstructor
@NoArgsConstructor
public class Associate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAssociate;
    @NotBlank
    private String fullName;
    @NotBlank
    private String politicalOffice;
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthday;
    @NotBlank
    private String sex;
    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;
}
