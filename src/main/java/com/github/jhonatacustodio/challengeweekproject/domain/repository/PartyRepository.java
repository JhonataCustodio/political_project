package com.github.jhonatacustodio.challengeweekproject.domain.repository;

import com.github.jhonatacustodio.challengeweekproject.domain.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PartyRepository extends JpaRepository<Party, String> {
    @Query(value =
            "SELECT p from Party p WHERE p.ideology like %:ideology%"
    )
    List<Party> searchByIdeology(@Param("ideology") String ideology);
}
