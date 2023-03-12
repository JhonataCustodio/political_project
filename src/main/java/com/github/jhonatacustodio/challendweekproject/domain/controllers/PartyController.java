package com.github.jhonatacustodio.challendweekproject.domain.controllers;

import com.github.jhonatacustodio.challendweekproject.domain.dto.request.PartyDtoRequest;
import com.github.jhonatacustodio.challendweekproject.domain.dto.response.PartyDtoResponse;
import com.github.jhonatacustodio.challendweekproject.domain.service.implementation.PartyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PartyController {
    @Autowired
    private PartyService partyService;

    @PostMapping("/parties")
    public ResponseEntity<PartyDtoResponse> save(@Valid @RequestBody PartyDtoRequest request){
        PartyDtoResponse partyDtoResponse = partyService.save(request);
        return ResponseEntity.ok(partyDtoResponse);
    }
    @GetMapping("/parties/ideology/{ideology}")
    public ResponseEntity<List<PartyDtoResponse>> getByIdeology(@RequestParam("ideology") String ideology){
        List<PartyDtoResponse> partyDtoResponse = partyService.getIdeology(ideology);
        return ResponseEntity.ok(partyDtoResponse);
    }
    @GetMapping("/parties/{id}")
    public ResponseEntity<PartyDtoResponse> getByIdParty(@PathVariable String id){
        PartyDtoResponse partyDtoResponse = partyService.getById(id);
        return ResponseEntity.ok(partyDtoResponse);
    }
    @PutMapping("/parties/{id}")
    public ResponseEntity<PartyDtoResponse> update(@Valid @PathVariable String id, @RequestBody PartyDtoRequest request){
        PartyDtoResponse partyDtoResponse = partyService.update(id, request);
        return ResponseEntity.ok(partyDtoResponse);
    }
    @DeleteMapping("/parties/{id}")
    public ResponseEntity<PartyDtoResponse> delete(@PathVariable String id){
        PartyDtoResponse partyDtoResponse = partyService.delete(id);
        return ResponseEntity.ok(partyDtoResponse);
    }
}
