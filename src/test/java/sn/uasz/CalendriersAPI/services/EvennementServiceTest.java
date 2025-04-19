package sn.uasz.CalendriersAPI.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sn.uasz.CalendriersAPI.dtos.EvennementDTO;
import sn.uasz.CalendriersAPI.entities.Evennement;
import sn.uasz.CalendriersAPI.exceptions.EvennementNotFoundException;
import sn.uasz.CalendriersAPI.mappers.EvennementMapper;
import sn.uasz.CalendriersAPI.repositories.EvennementRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EvennementServiceTest {
    @Mock
    private EvennementRepository evennementRepository;

    @Mock
    private EvennementMapper evennementMapper;

    @InjectMocks
    private EvennementServiceImpl indertest; // ✅ L'implémentation concrète ici

    @Test
    void shouldSaveEvennement() {
        EvennementDTO evennementDTO = EvennementDTO.builder()
                .titre("Paque")
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.EPOCH)
                .emplacement("ZIG")
                .type("ceremonie")
                .build();

        Evennement evennement = Evennement.builder()
                .titre("Paque")
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.EPOCH)
                .emplacement("ZIG")
                .type("ceremonie")
                .build();

        Evennement savedEvennement = Evennement.builder()
                .id(1L)
                .titre("Paque")
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.EPOCH)
                .emplacement("ZIG")
                .type("ceremonie")
                .build();

        EvennementDTO expected = EvennementDTO.builder()
                .id(1L)
                .titre("Paque")
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.EPOCH)
                .emplacement("ZIG")
                .type("ceremonie")
                .build();

        when(evennementMapper.formEvennementDTO(evennementDTO)).thenReturn(evennement);
        when(evennementRepository.save(evennement)).thenReturn(savedEvennement);
        when(evennementMapper.formEvennement(savedEvennement)).thenReturn(expected);

        EvennementDTO result = indertest.ajouterEvennement(evennementDTO);

        assertThat(result).isNotNull();
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
    @Test
    void shouldAllEvennement() {
        // 1. Create test data with fixed dates
        LocalDate testDate = LocalDate.of(2024, 1, 1);

        List<Evennement> evennements = List.of(
                Evennement.builder().id(1L).titre("Paque").dateDebut(testDate).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build(),
                Evennement.builder().id(2L).titre("Tabaski").dateDebut(testDate).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build(),
                Evennement.builder().id(3L).titre("Magal").dateDebut(testDate).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build()
        );

        List<EvennementDTO> expectedDTOs = List.of(
                EvennementDTO.builder().id(1L).titre("Paque").dateDebut(testDate).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build(),
                EvennementDTO.builder().id(2L).titre("Tabaski").dateDebut(testDate).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build(),
                EvennementDTO.builder().id(3L).titre("Magal").dateDebut(testDate).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build()
        );

        // 2. Setup mocks to match service implementation
        when(evennementRepository.findAll()).thenReturn(evennements);

        // Mock individual mappings for each element
        when(evennementMapper.formEvennement(evennements.get(0))).thenReturn(expectedDTOs.get(0));
        when(evennementMapper.formEvennement(evennements.get(1))).thenReturn(expectedDTOs.get(1));
        when(evennementMapper.formEvennement(evennements.get(2))).thenReturn(expectedDTOs.get(2));

        // 3. Call method under test
        List<EvennementDTO> result = indertest.afficherEvennements();

        // 4. Verify the result
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expectedDTOs);

        // 5. Verify mock interactions
        verify(evennementRepository).findAll();
        verify(evennementMapper).formEvennement(evennements.get(0));
        verify(evennementMapper).formEvennement(evennements.get(1));
        verify(evennementMapper).formEvennement(evennements.get(2));
    }

    @Test
    void shouldUpdateEvennement() throws EvennementNotFoundException {
        Long evennementId = 3L;

        EvennementDTO evennementDTO = EvennementDTO.builder()
                .id(3L)
                .titre("Paque")
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.EPOCH)
                .emplacement("ZIG")
                .type("ceremonie")
                .build();

        Evennement evennement = Evennement.builder()
                .id(3L)
                .titre("Paque")
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.EPOCH)
                .emplacement("ZIG")
                .type("ceremonie")
                .build();

        Evennement updateEvennement = Evennement.builder()
                .id(3L)
                .titre("Paque")
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.EPOCH)
                .emplacement("ZIG")
                .type("ceremonie")
                .build();

        EvennementDTO expected = EvennementDTO.builder()
                .id(3L)
                .titre("Paque")
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.EPOCH)
                .emplacement("ZIG")
                .type("ceremonie")
                .build();

        when(evennementRepository.findById(evennementId)).thenReturn(Optional.of(evennement));
        when(evennementMapper.formEvennementDTO(evennementDTO)).thenReturn(evennement);
        when(evennementRepository.save(evennement)).thenReturn(updateEvennement);
        when(evennementMapper.formEvennement(updateEvennement)).thenReturn(expected);

        EvennementDTO result = indertest.modifierEvennement(evennementId, evennementDTO);

        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
    @Test
    void shouldDeleteEvennement() throws EvennementNotFoundException {
        Long evennementId = 3L;
        Evennement evennement = Evennement.builder()
                .id(3L)
                .titre("Paque")
                .dateDebut(LocalDate.now())
                .dateFin(LocalDate.EPOCH)
                .emplacement("ZIG")
                .type("ceremonie")
                .build();
        when(evennementRepository.findById(evennementId)).thenReturn(Optional.of(evennement));
        indertest.supprimerEvennement(evennementId);
        verify(evennementRepository).deleteById(evennementId);
    }
    @Test
    void shouldNotDeleteEvennementNotExit()  {
        Long evennementId = 3L;
        when(evennementRepository.findById(evennementId)).thenReturn(Optional.empty());
        assertThatThrownBy(()->indertest.supprimerEvennement(evennementId))
                .isInstanceOf(EvennementNotFoundException.class);

    }

}
