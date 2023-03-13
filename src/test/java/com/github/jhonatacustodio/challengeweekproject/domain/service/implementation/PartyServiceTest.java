package com.github.jhonatacustodio.challengeweekproject.domain.service.implementation;

import com.github.jhonatacustodio.challengeweekproject.config.ApplicationConfig;
import com.github.jhonatacustodio.challengeweekproject.domain.dto.request.PartyDtoRequest;
import com.github.jhonatacustodio.challengeweekproject.domain.dto.response.PartyDtoResponse;
import com.github.jhonatacustodio.challengeweekproject.domain.entity.Party;
import com.github.jhonatacustodio.challengeweekproject.domain.enums.Ideology;
import com.github.jhonatacustodio.challengeweekproject.domain.exceptionHandler.PartyNotFoundException;
import com.github.jhonatacustodio.challengeweekproject.domain.repository.PartyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
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
    public static final int INDEX = 0;
    @InjectMocks
    private PartyService partyService;
    @Mock
    private PartyRepository partyRepository;
    @Spy
    private ModelMapper modelMapper = new ApplicationConfig().modelMapper();
    @Mock
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
    void whenCreateThenReturnSuccess() {
        Mockito.when(partyRepository.save(Mockito.any())).thenReturn(party);

        Mockito.when(modelMapper.map(party, PartyDtoResponse.class)).thenReturn(partyDtoResponse);
        PartyDtoResponse response = partyService.save(partyDtoRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(PartyDtoResponse.class, response.getClass());
        //Assertions.assertEquals(ID_PARTY, response.getIdParty());
        Assertions.assertEquals(PARTY_NAME, response.getPartyName());
        Assertions.assertEquals(ACRONYM, response.getAcronym());
        Assertions.assertEquals(IDEOLOGY, response.getIdeology());
        Assertions.assertEquals(FOUNDATION_DATE, response.getFoundationDate());

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
    //Teste com problema
    @Test
    void whenGetAllPartyThenReturnPartyList() {
        Mockito.when(partyRepository.findAll()).thenReturn(List.of(party));
        Mockito.when(modelMapper.map(party, PartyDtoResponse.class)).thenReturn(partyDtoResponse);

        List<PartyDtoResponse> response = partyService.getAllParty();

        Assertions.assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(PartyDtoResponse.class, response.get(INDEX).getClass());
        assertEquals(ID_PARTY, response.get(INDEX).getIdParty());
        assertEquals(PARTY_NAME, response.get(INDEX).getPartyName());
        assertEquals(ACRONYM, response.get(INDEX).getAcronym());
        assertEquals(IDEOLOGY, response.get(INDEX).getIdeology());
        assertEquals(FOUNDATION_DATE, response.get(INDEX).getFoundationDate());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }


    private void starterParty(){
        party = new Party(ID_PARTY, PARTY_NAME, ACRONYM, IDEOLOGY, FOUNDATION_DATE);
        partyDtoRequest = new PartyDtoRequest(PARTY_NAME, ACRONYM, IDEOLOGY, FOUNDATION_DATE);
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