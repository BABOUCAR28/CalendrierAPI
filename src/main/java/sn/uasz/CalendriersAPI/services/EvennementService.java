package sn.uasz.CalendriersAPI.services;

import sn.uasz.CalendriersAPI.dtos.EvennementDTO;

import java.util.List;

public interface EvennementService {
    public EvennementDTO ajouterEvennement(EvennementDTO evennementDTO);

    public EvennementDTO modifierEvennement(EvennementDTO evennementDTO);

}
