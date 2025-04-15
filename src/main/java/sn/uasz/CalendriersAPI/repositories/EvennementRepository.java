package sn.uasz.CalendriersAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.uasz.CalendriersAPI.entities.Evennement;

public interface EvennementRepository extends JpaRepository<Evennement, Long> {

}
