package sn.uasz.CalendriersAPI.services;

import sn.uasz.CalendriersAPI.dtos.EvennementDTO;
import sn.uasz.CalendriersAPI.exceptions.EvennementNotFoundException;

import java.util.List;

public interface EvennementService {
    public EvennementDTO ajouterEvennement(EvennementDTO evennementDTO);
    public List<EvennementDTO> afficherEvennements();
    public void supprimerEvennement(Long evennementid) throws EvennementNotFoundException;

    public EvennementDTO modifierEvennement(Long evennementId,EvennementDTO evennementDTO) throws EvennementNotFoundException;

}
