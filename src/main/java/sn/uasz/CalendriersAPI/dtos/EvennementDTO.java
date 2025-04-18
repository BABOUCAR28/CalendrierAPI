package sn.uasz.CalendriersAPI.dtos;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class EvennementDTO {
    private Long id;
    private String titre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String emplacement;
    private String type;
}
