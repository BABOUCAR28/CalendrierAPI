package sn.uasz.CalendriersAPI.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import sn.uasz.CalendriersAPI.dtos.EvennementDTO;
import sn.uasz.CalendriersAPI.entities.Evennement;
import sn.uasz.CalendriersAPI.repositories.EvennementRepository;

import java.nio.Buffer;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvennementMapper {
    private ModelMapper modelMapper=new ModelMapper();
    private EvennementRepository evennementRepository;
    public EvennementDTO formEvennement(Evennement evennement) {
        EvennementDTO evennementDTO = new EvennementDTO();
        BeanUtils.copyProperties(evennement, evennementDTO);
        return evennementDTO;
    }
    public Evennement formEvennementDTO(EvennementDTO evennementDTO) {
        Evennement evennement = new Evennement();
        BeanUtils.copyProperties(evennementDTO, evennement);
        return evennement;
    }
    public List<EvennementDTO> formEvennements(List<Evennement> evennements) {
        return evennements.stream().map(evennement -> modelMapper.map(evennement, EvennementDTO.class)).collect(Collectors.toList());
    }
}
