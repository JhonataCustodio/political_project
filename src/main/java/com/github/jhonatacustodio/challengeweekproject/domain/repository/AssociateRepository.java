package com.github.jhonatacustodio.challengeweekproject.domain.repository;

import com.github.jhonatacustodio.challengeweekproject.domain.entity.Associate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


@ComponentScan(basePackages = "com.github.jhonatacustodio.challendweekproject.domain.repository.AssociateRepository")
@Configuration
public interface AssociateRepository extends JpaRepository<Associate, Integer> {
    @Query(value =
            "SELECT a from Associate a WHERE a.fullName like %:fullName%"
    )
    List<Associate> searchByName(@Param("fullName") String fullName);

    @Query(value =
            "SELECT a from Associate a WHERE a.politicalOffice like %:politicalOffice%"
    )
    List<Associate> searchByPoliticalOffice(@Param("politicalOffice") String politicalOffice);

}
