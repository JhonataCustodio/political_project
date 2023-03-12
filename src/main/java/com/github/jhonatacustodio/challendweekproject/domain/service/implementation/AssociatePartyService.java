package com.github.jhonatacustodio.challendweekproject.domain.service.implementation;

import com.github.jhonatacustodio.challendweekproject.domain.dto.request.AssociatePartyDtoRequest;
import com.github.jhonatacustodio.challendweekproject.domain.dto.response.AssociatePartyDtoResponse;
import com.github.jhonatacustodio.challendweekproject.domain.entity.Associate;
import com.github.jhonatacustodio.challendweekproject.domain.entity.AssociateParty;
import com.github.jhonatacustodio.challendweekproject.domain.entity.Party;
import com.github.jhonatacustodio.challendweekproject.domain.exceptionHandler.AssociatePartyNotFoundException;
import com.github.jhonatacustodio.challendweekproject.domain.repository.AssociatePartyRepository;
import com.github.jhonatacustodio.challendweekproject.domain.repository.AssociateRepository;
import com.github.jhonatacustodio.challendweekproject.domain.repository.PartyRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociatePartyService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AssociatePartyRepository associatePartyRepository;
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private AssociateRepository associateRepository;
    @Transactional
    public AssociatePartyDtoResponse save(AssociatePartyDtoRequest request){
        Associate associate = associateRepository.findById(request.getIdAssociate())
                .orElseThrow(() -> new AssociatePartyNotFoundException());

        Party party = partyRepository.findById(request.getIdParty())
                .orElseThrow(() -> new AssociatePartyNotFoundException());

        AssociateParty associateParty = new AssociateParty();
        associateParty.setIdAssociate(associate.getIdAssociate());
        associateParty.setIdParty(party.getIdParty());
        associatePartyRepository.save(associateParty);

        AssociatePartyDtoResponse associatePartyDtoResponse = modelMapper.map(associateParty, AssociatePartyDtoResponse.class);
        return associatePartyDtoResponse;

    }
    public AssociatePartyDtoResponse delete(Integer idAssociate, String idParty) {
        Optional<AssociateParty> associatePartyOpt = associatePartyRepository.findByIdAssociateIdParty(idAssociate, idParty);
        AssociateParty associateParty = associatePartyOpt.orElseThrow(() -> new AssociatePartyNotFoundException());
        associatePartyRepository.delete(associateParty);
        return modelMapper.map(associateParty, AssociatePartyDtoResponse.class);
    }
    public List<AssociatePartyDtoResponse> getByAssociateParty(String idParty){
        List<AssociateParty> associateParties = associatePartyRepository.findByParty(idParty);
        List<AssociatePartyDtoResponse> partyDtoResponses = associateParties
                .stream().map(associateParty -> modelMapper.map(associateParty, AssociatePartyDtoResponse.class)
                ).collect(Collectors.toList());
        return partyDtoResponses;
    }

}
