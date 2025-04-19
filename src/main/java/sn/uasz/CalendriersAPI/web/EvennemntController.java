package sn.uasz.CalendriersAPI.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.uasz.CalendriersAPI.dtos.EvennementDTO;
import sn.uasz.CalendriersAPI.exceptions.EvennementNotFoundException;
import sn.uasz.CalendriersAPI.services.EvennementService;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class EvennemntController {
    private EvennementService evennementService;

    @PostMapping("/evennements")
    public ResponseEntity<EvennementDTO> ajouterEvennement(@RequestBody EvennementDTO evennementDTO) {
        EvennementDTO created = evennementService.ajouterEvennement(evennementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping("/afficherEvennement")
    public List<EvennementDTO> afficherEvennements(){
        return evennementService.afficherEvennements();
    }
    @PutMapping("/evennements/{id}")
    public EvennementDTO modifierEvennement(@PathVariable(name = "id") Long evennementId,@RequestBody EvennementDTO evennementDTO) throws EvennementNotFoundException {
        return evennementService.modifierEvennement(evennementId,evennementDTO);
    }
    @DeleteMapping("/supprimerEvennement")
    public void supprimerEvennement(Long evennementId) throws EvennementNotFoundException {
        evennementService.supprimerEvennement(evennementId);
    }

}
