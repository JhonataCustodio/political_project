package com.github.jhonatacustodio.challendweekproject.domain.controllers;

import com.github.jhonatacustodio.challendweekproject.domain.dto.request.AssociateDtoRequest;
import com.github.jhonatacustodio.challendweekproject.domain.dto.response.AssociateDtoResponse;
import com.github.jhonatacustodio.challendweekproject.domain.service.implementation.AssociateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssociateController {
    @Autowired
    private AssociateService associateService;

    @PostMapping("/associates")
    public ResponseEntity<AssociateDtoResponse> save(@Valid @RequestBody AssociateDtoRequest request){
        AssociateDtoResponse associateDtoResponse = associateService.save(request);
        return ResponseEntity.ok(associateDtoResponse);
    }
    @GetMapping("/associates/{id}")
    public ResponseEntity<AssociateDtoResponse> getById(@PathVariable Integer id){
        AssociateDtoResponse associateDtoResponse = associateService.getById(id);
        return ResponseEntity.ok(associateDtoResponse);
    }
    @GetMapping("/associates")
    public ResponseEntity<List<AssociateDtoResponse>> getAllAssociate(){
        List<AssociateDtoResponse> associateDtoResponses = associateService.getAllAssociate();
        return ResponseEntity.ok(associateDtoResponses);
    }
    @PutMapping("/associates/{id}")
    public ResponseEntity<AssociateDtoResponse> update(@Valid @PathVariable Integer id, @RequestBody AssociateDtoRequest request){
        AssociateDtoResponse associateDtoResponse = associateService.update(id, request);
        return ResponseEntity.ok(associateDtoResponse);
    }
    @GetMapping("/associates/fullName")
    public ResponseEntity<List<AssociateDtoResponse>> getByName(@RequestParam("fullName") String fullName) {
        List<AssociateDtoResponse> associateDtoResponses = associateService.getByName(fullName);
        return ResponseEntity.ok(associateDtoResponses);
    }
    @GetMapping("/associates/politicalOffice")
    public ResponseEntity<List<AssociateDtoResponse>> getByPoliticalOffice(@RequestParam("politicalOffice") String politicalOffice){
        List<AssociateDtoResponse> associateDtoResponses = associateService.getByPoliticalOffice(politicalOffice);
        return ResponseEntity.ok(associateDtoResponses);
    }
    @DeleteMapping("/associates/{id}")
    public ResponseEntity<AssociateDtoResponse> delete(@PathVariable Integer id){
        AssociateDtoResponse associateDtoResponse = associateService.delete(id);
        return ResponseEntity.ok(associateDtoResponse);
    }
}
