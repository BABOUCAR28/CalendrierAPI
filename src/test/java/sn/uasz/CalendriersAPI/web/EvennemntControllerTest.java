package sn.uasz.CalendriersAPI.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sn.uasz.CalendriersAPI.dtos.EvennementDTO;
import sn.uasz.CalendriersAPI.services.EvennementService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(EvennemntController.class)

class EvennementControllerTest {

    @MockBean
    private EvennementService evennementService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    List<EvennementDTO> evennements;

    @BeforeEach
    void setUp() {
        evennements = List.of(
                EvennementDTO.builder().id(1L).titre("Paque").dateDebut(LocalDate.now()).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build(),
                EvennementDTO.builder().id(2L).titre("Tabaski").dateDebut(LocalDate.now()).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build(),
                EvennementDTO.builder().id(3L).titre("Magal").dateDebut(LocalDate.now()).dateFin(LocalDate.EPOCH).emplacement("ZIG").type("ceremonie").build()
        );
    }

    @Test
    void shouldSaveEvennement() throws Exception {
        EvennementDTO evennementDTO = evennements.get(0);
        when(evennementService.ajouterEvennement(any(EvennementDTO.class))).thenReturn(evennementDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/evennements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(evennementDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())  // Vérifie que le statut est 201
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(evennementDTO)));  // Vérifie que la réponse contient les données attendues
    }
    @Test
    void shouldAllEvennements() {

    }

    @Test
    void shouldUpdateEvennement() throws Exception {
        Long evennementId = 1L;
        EvennementDTO evennementDTO = evennements.get(0);
        when(evennementService.modifierEvennement(eq(evennementId), any())).thenReturn(evennementDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/evennements/{id}", evennementId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(evennementDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(evennementDTO)));
    }
}
