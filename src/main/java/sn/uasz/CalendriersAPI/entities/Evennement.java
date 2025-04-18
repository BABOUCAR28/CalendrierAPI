package sn.uasz.CalendriersAPI.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
 @NoArgsConstructor @AllArgsConstructor @Getter
@Setter @ToString @Builder
@Table(name = "evenements")
public class Evennement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String emplacement;
    private String type;
}
