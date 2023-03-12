package com.github.jhonatacustodio.challendweekproject.domain.service.implementation;

import com.github.jhonatacustodio.challendweekproject.domain.dto.request.PartyDtoRequest;
import com.github.jhonatacustodio.challendweekproject.domain.dto.response.PartyDtoResponse;
import com.github.jhonatacustodio.challendweekproject.domain.entity.Party;
import com.github.jhonatacustodio.challendweekproject.domain.enums.Ideology;
import com.github.jhonatacustodio.challendweekproject.domain.exceptionHandler.PartyNotFoundException;
import com.github.jhonatacustodio.challendweekproject.domain.repository.PartyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PartyServiceTest {
    public static final String ID_PARTY = "p23588";
    public static final String PARTY_NAME = "Democratic Party";
    public static final String ACRONYM = "DP";
    public static final String IDEOLOGY = "Left";
    public static final String FOUNDATION_DATE = "2005-03-02";
    @InjectMocks
    private PartyService partyService;
    @Mock
    private PartyRepository partyRepository;
    @Mock
    private ModelMapper modelMapper;
    private Party party;
    private PartyDtoResponse partyDtoResponse;
    private PartyDtoRequest partyDtoRequest;
    private List<Party> partyList;
    private List<String> ideologyValues;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        starterParty();
    }

    @Test
    void save() {
    }

    @Test
    void getIdeology() {
    }

    @Test
    void whenFindByIdThenReturnAndPartyInstance() {
        Mockito.when(partyRepository.findById(Mockito.anyString())).thenReturn(Optional.of(party));
        Mockito.when(modelMapper.map(party, PartyDtoResponse.class)).thenReturn(partyDtoResponse);

        PartyDtoResponse response = partyService.getById(ID_PARTY);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(partyDtoResponse.getIdParty(), response.getIdParty());
        Assertions.assertEquals(partyDtoResponse.getPartyName(), response.getPartyName());
        Assertions.assertEquals(partyDtoResponse.getAcronym(), response.getAcronym());
        Assertions.assertEquals(partyDtoResponse.getIdeology(), response.getIdeology());
        Assertions.assertEquals(partyDtoResponse.getFoundationDate(), response.getFoundationDate());

        Mockito.verify(partyRepository, Mockito.times(1)).findById(ID_PARTY);
    }
    @Test
    void  whenFindByIdTheReturnAnObjectNotFoundException(){
        Mockito.when(partyRepository.findById(Mockito.anyString())).thenThrow(new PartyNotFoundException());
        try{
            partyService.getById(ID_PARTY);
        } catch (Exception exception){
            assertEquals(PartyNotFoundException.class, exception.getClass());
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void starterParty(){
        party = new Party(ID_PARTY, PARTY_NAME, ACRONYM, IDEOLOGY, FOUNDATION_DATE);
        partyDtoRequest = new PartyDtoRequest( PARTY_NAME, ACRONYM, IDEOLOGY, FOUNDATION_DATE);
        partyDtoResponse = new PartyDtoResponse(ID_PARTY, PARTY_NAME, ACRONYM, IDEOLOGY, FOUNDATION_DATE);
        partyList = List.of(new Party(ID_PARTY, PARTY_NAME, ACRONYM, IDEOLOGY, FOUNDATION_DATE));
        ideologyValues = getIdeologyValues();
    }
    private List<String> getIdeologyValues() {
        return Arrays.stream(Ideology.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}