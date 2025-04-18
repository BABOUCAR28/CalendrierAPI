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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

        Mockito.when(evennementMapper.formEvennementDTO(evennementDTO)).thenReturn(evennement);
        Mockito.when(evennementRepository.save(evennement)).thenReturn(savedEvennement);
        Mockito.when(evennementMapper.formEvennement(savedEvennement)).thenReturn(expected);

        EvennementDTO result = indertest.ajouterEvennement(evennementDTO);

        assertThat(result).isNotNull();
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
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

        Mockito.when(evennementRepository.findById(evennementId)).thenReturn(Optional.of(evennement));
        Mockito.when(evennementMapper.formEvennementDTO(evennementDTO)).thenReturn(evennement);
        Mockito.when(evennementRepository.save(evennement)).thenReturn(updateEvennement);
        Mockito.when(evennementMapper.formEvennement(updateEvennement)).thenReturn(expected);

        EvennementDTO result = indertest.modifierEvennement(evennementId, evennementDTO);

        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
}
