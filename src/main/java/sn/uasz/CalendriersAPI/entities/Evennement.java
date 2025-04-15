package sn.uasz.CalendriersAPI.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
 @NoArgsConstructor @AllArgsConstructor @Getter
@Setter @ToString
@Table(name = "evenements")
public class Evennement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private Date dateDebut;
    private Date dateFin;
    private String emplacement;
    private String type;
}
