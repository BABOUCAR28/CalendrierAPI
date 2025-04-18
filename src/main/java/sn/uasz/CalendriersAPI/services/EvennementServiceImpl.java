package sn.uasz.CalendriersAPI.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sn.uasz.CalendriersAPI.dtos.EvennementDTO;
import sn.uasz.CalendriersAPI.entities.Evennement;
import sn.uasz.CalendriersAPI.exceptions.EvennementNotFoundException;
import sn.uasz.CalendriersAPI.mappers.EvennementMapper;
import sn.uasz.CalendriersAPI.repositories.EvennementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class EvennementServiceImpl implements EvennementService {
    private EvennementRepository evennementRepository;
    private EvennementMapper evennementMapper;

    @Override
    public EvennementDTO ajouterEvennement(EvennementDTO evennementDTO) {
        log.info("ajouter Evennement");
        if (evennementDTO.getId() == null) {
            Evennement evennement=evennementMapper.formEvennementDTO(evennementDTO);
            Evennement ajouterEvennement = evennementRepository.save(evennement);
            return evennementMapper.formEvennement(ajouterEvennement);

        }
        else
        return null;
    }


    @Override
    public EvennementDTO modifierEvennement(Long evennementId,EvennementDTO evennementDTO) throws EvennementNotFoundException {
        Optional<Evennement> evennemen=evennementRepository.findById(evennementDTO.getId());
        if(evennemen.isEmpty()) throw new EvennementNotFoundException("Evennement not found");
        evennementDTO.setId(evennementDTO.getId());
        log.info("modifier Evennement");
        Evennement evennement=evennementMapper.formEvennementDTO(evennementDTO);
        Evennement modifierEvennement = evennementRepository.save(evennement);
        return evennementMapper.formEvennement(modifierEvennement);
    }


}
