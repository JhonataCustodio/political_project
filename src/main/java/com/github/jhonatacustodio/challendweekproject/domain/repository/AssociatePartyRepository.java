package com.github.jhonatacustodio.challendweekproject.domain.repository;

import com.github.jhonatacustodio.challendweekproject.domain.entity.AssociateParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssociatePartyRepository extends JpaRepository<AssociateParty, Integer> {
    @Query("SELECT ap FROM AssociateParty ap WHERE ap.idAssociate = :idAssociate AND ap.idParty = :idParty")
    Optional<AssociateParty> findByIdAssociateIdParty(@Param("idAssociate") Integer idAssociate, @Param("idParty") String idParty);

    @Query("SELECT ap FROM AssociateParty ap WHERE ap.idParty = :idParty")
    List<AssociateParty> findByParty(@Param("idParty") String idParty);
}
