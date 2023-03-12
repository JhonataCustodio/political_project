package com.github.jhonatacustodio.challendweekproject.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociateParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_associate")
    @NotNull
    @Min(value = 1)
    private Integer idAssociate;
    @Column(name = "id_party")
    @NotBlank
    private String idParty;
}
