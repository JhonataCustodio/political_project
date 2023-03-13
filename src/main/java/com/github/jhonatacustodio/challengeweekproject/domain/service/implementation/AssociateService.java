package com.github.jhonatacustodio.challengeweekproject.domain.service.implementation;

import com.github.jhonatacustodio.challengeweekproject.domain.dto.request.AssociateDtoRequest;
import com.github.jhonatacustodio.challengeweekproject.domain.dto.response.AssociateDtoResponse;
import com.github.jhonatacustodio.challengeweekproject.domain.entity.Associate;
import com.github.jhonatacustodio.challengeweekproject.domain.enums.PoliticalOffice;
import com.github.jhonatacustodio.challengeweekproject.domain.enums.Sex;
import com.github.jhonatacustodio.challengeweekproject.domain.exceptionHandler.AssociateInvalidFieldException;
import com.github.jhonatacustodio.challengeweekproject.domain.exceptionHandler.AssociateNotFoundException;
import com.github.jhonatacustodio.challengeweekproject.domain.repository.AssociateRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociateService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AssociateRepository associateRepository;
    @Transactional
    public AssociateDtoResponse save(AssociateDtoRequest request){
        Associate associate = modelMapper.map(request, Associate.class);
        associateRepository.save(associate);
        List<String> politicalOffice = Arrays.stream(PoliticalOffice.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        List<String> sex = Arrays.stream(Sex.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        if(!politicalOffice.contains(associate.getPoliticalOffice().toUpperCase())){
            throw new AssociateInvalidFieldException();
        } else if (!sex.contains(associate.getSex().toUpperCase())) {
            throw new AssociateInvalidFieldException();
        } else {
            return modelMapper.map(associate, AssociateDtoResponse.class);
        }
    }
    public AssociateDtoResponse getById(Integer id){
        Associate associate = associateRepository.findById(id)
                .orElseThrow(() -> new AssociateNotFoundException());
        return modelMapper.map(associate, AssociateDtoResponse.class);
    }
    public List<AssociateDtoResponse> getByName(String fullName){
        List<Associate> associates = associateRepository.searchByName(fullName);
        List<AssociateDtoResponse> associateDtoResponses = associates
                .stream().map(associate -> modelMapper.map(associate, AssociateDtoResponse.class))
                .collect(Collectors.toList());
        return associateDtoResponses;

    }
    public List<AssociateDtoResponse> getByPoliticalOffice(String politicalOffice){
        List<Associate> associates = associateRepository.searchByPoliticalOffice(politicalOffice);
        List<AssociateDtoResponse> associateDtoResponses = associates
                .stream().map(associate -> modelMapper.map(associate, AssociateDtoResponse.class))
                .collect(Collectors.toList());
        return associateDtoResponses;
    }
    public AssociateDtoResponse update(Integer id, AssociateDtoRequest request){
        Associate associate = associateRepository.findById(id)
                .orElseThrow(()-> new AssociateNotFoundException());
        associate.setFullName(request.getFullName());
        associate.setPoliticalOffice(request.getPoliticalOffice());
        associate.setBirthday(request.getBirthday());
        associate.setSex(request.getSex());
        associateRepository.save(associate);
        AssociateDtoResponse associateDtoResponse = modelMapper.map(associate, AssociateDtoResponse.class);
        return associateDtoResponse;
    }
    public AssociateDtoResponse delete(Integer id){
        Associate associate = associateRepository.findById(id)
                .orElseThrow(() -> new AssociateNotFoundException());
        associateRepository.delete(associate);
        return modelMapper.map(associate, AssociateDtoResponse.class);
    }
    public List<AssociateDtoResponse> getAllAssociate(){
        List<Associate> associates = associateRepository.findAll();
        return associates.stream().map(
                this::convertAssociateToDto
        ).collect(Collectors.toList());
    }
    public AssociateDtoResponse convertAssociateToDto(Associate associate){
        AssociateDtoResponse associateDtoResponse = modelMapper.map(associate, AssociateDtoResponse.class);
        return associateDtoResponse;
    }
}
