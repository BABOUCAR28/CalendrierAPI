package sn.uasz.CalendriersAPI.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sn.uasz.CalendriersAPI.dtos.EvennementDTO;
import sn.uasz.CalendriersAPI.services.EvennementService;

import java.util.List;

@AllArgsConstructor
@RestController
public class EvennemntController {
    private EvennementService evennementService;

    @PostMapping("/ajouterEvennement")
    public EvennementDTO ajouterEvennement(EvennementDTO evennementDTO) {
        return evennementService.ajouterEvennement(evennementDTO);
    }

   
    @PutMapping("/modifierEvennement")
    public EvennementDTO modifierEvennement(EvennementDTO evennementDTO) {
        return evennementService.modifierEvennement(evennementDTO);
    }
}
