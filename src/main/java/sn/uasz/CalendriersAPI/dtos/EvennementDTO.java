package sn.uasz.CalendriersAPI.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
public class EvennementDTO {
    private Long id;
    private String titre;
    private Date dateDebut;
    private Date dateFin;
    private String emplacement;
    private String type;
}
