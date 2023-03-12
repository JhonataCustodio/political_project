package com.github.jhonatacustodio.challendweekproject.domain.service.implementation;

import com.github.jhonatacustodio.challendweekproject.domain.dto.request.PartyDtoRequest;
import com.github.jhonatacustodio.challendweekproject.domain.dto.response.PartyDtoResponse;
import com.github.jhonatacustodio.challendweekproject.domain.entity.Party;
import com.github.jhonatacustodio.challendweekproject.domain.enums.Ideology;
import com.github.jhonatacustodio.challendweekproject.domain.exceptionHandler.PartyInvalidFielException;
import com.github.jhonatacustodio.challendweekproject.domain.exceptionHandler.PartyNotFoundException;
import com.github.jhonatacustodio.challendweekproject.domain.repository.PartyRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PartyService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PartyRepository partyRepository;

    @Transactional
    public PartyDtoResponse save(PartyDtoRequest request){
        Party party = modelMapper.map(request, Party.class);
        String idParty = "p" + String.format("%05d", new Random().nextInt(100000));
        party.setIdParty(idParty);
        partyRepository.save(party);
        List<String> ideology = Arrays.stream(Ideology.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        if(!ideology.contains(party.getIdeology().toUpperCase())){
            throw new PartyInvalidFielException();
        }
        else {
            return modelMapper.map(party, PartyDtoResponse.class);
        }

    }
    public List<PartyDtoResponse> getIdeology(String ideology){
        List<Party> parties = partyRepository.searchByIdeology(ideology);
        List<PartyDtoResponse> partyDtoResponses = parties.stream()
                .map(party -> modelMapper.map(party, PartyDtoResponse.class)).collect(Collectors.toList());
        return partyDtoResponses;
    }
    public PartyDtoResponse getById(String idParty){
        Party party = partyRepository.findById(idParty)
                .orElseThrow(()-> new PartyNotFoundException());
        return modelMapper.map(party,PartyDtoResponse.class);
    }
    public PartyDtoResponse update(String idParty, PartyDtoRequest request){
        Party party = partyRepository.findById(idParty)
                .orElseThrow(()-> new PartyNotFoundException());
        party.setPartyName(request.getPartyName());
        party.setAcronym(request.getAcronym());
        party.setIdeology(request.getIdeology());
        party.setFoundationDate(request.getFoundationDate());
        partyRepository.save(party);
        PartyDtoResponse partyDtoResponse = modelMapper.map(party, PartyDtoResponse.class);
        return partyDtoResponse;
    }
    public PartyDtoResponse delete(String idParty){
        Party party = partyRepository.findById(idParty)
                .orElseThrow(()-> new PartyNotFoundException());
        partyRepository.delete(party);
        return modelMapper.map(party, PartyDtoResponse.class);
    }
    public List<PartyDtoResponse> getAllParty(){
        List<Party> parties = partyRepository.findAll();
        return parties.stream().map(
                this::convertPartyToDto
        ).collect(Collectors.toList());
    }
    public PartyDtoResponse convertPartyToDto(Party party){
        PartyDtoResponse partyDtoResponse = modelMapper.map(party, PartyDtoResponse.class);
        return partyDtoResponse;
    }

}
