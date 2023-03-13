package com.github.jhonatacustodio.challengeweekproject.domain.controllers;

import com.github.jhonatacustodio.challengeweekproject.domain.dto.request.AssociatePartyDtoRequest;
import com.github.jhonatacustodio.challengeweekproject.domain.dto.response.AssociatePartyDtoResponse;
import com.github.jhonatacustodio.challengeweekproject.domain.service.implementation.AssociatePartyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssociatePartyController {
    @Autowired
    private AssociatePartyService associatePartyService;

    @PostMapping("/associates/parties")
    public ResponseEntity<AssociatePartyDtoResponse> save(@Valid @RequestBody AssociatePartyDtoRequest request){
        AssociatePartyDtoResponse associatePartyDtoResponse = associatePartyService.save(request);
        return ResponseEntity.ok(associatePartyDtoResponse);
    }
    @DeleteMapping("/associates/{idAssociate}/parties/{idParty}")
    public ResponseEntity<AssociatePartyDtoResponse> delete(@PathVariable Integer idAssociate,@PathVariable String idParty){
        AssociatePartyDtoResponse associatePartyDtoResponse = associatePartyService.delete(idAssociate, idParty);
        return ResponseEntity.ok(associatePartyDtoResponse);
    }
    @GetMapping("/parties/{idParty}/associates")
    public ResponseEntity<List<AssociatePartyDtoResponse>> getByAssociateParty(@PathVariable String idParty){
        List<AssociatePartyDtoResponse> partyDtoResponses = associatePartyService.getByAssociateParty(idParty);
        return ResponseEntity.ok(partyDtoResponses);
    }
}
