package sn.uasz.CalendriersAPI.mappers;


import org.junit.jupiter.api.Test;
import sn.uasz.CalendriersAPI.dtos.EvennementDTO;
import sn.uasz.CalendriersAPI.entities.Evennement;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EvennementMapperTest {
    EvennementMapper inderTest=new EvennementMapper();
    @Test
    void shouldMapEvennementToEvennementDTO() {
        Evennement givenEvennement=Evennement.builder().id(1L).titre("Paque").dateDebut(LocalDate.now()).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build();
        EvennementDTO expected=EvennementDTO.builder().id(1L).titre("Paque").dateDebut(LocalDate.now()).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build();
        EvennementDTO result = inderTest.formEvennement(givenEvennement);
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
    @Test
    void shouldMapEvennementDTOToEvennement() {
        EvennementDTO givenEvennementDTO=EvennementDTO.builder().id(1L).titre("Paque").dateDebut(LocalDate.now()).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build();
        Evennement expected=Evennement.builder().id(1L).titre("Paque").dateDebut(LocalDate.now()).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build();

        Evennement result = inderTest.formEvennementDTO(givenEvennementDTO);
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

}