package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PunktRepository extends JpaRepository<Punkt, Long> {

  @Transactional
  @Query("SELECT p FROM Punkt p WHERE p.nutzer.id = ?1")
  List<Punkt> findAllByNutzerId(Long id);

  @Transactional
  @Query("SELECT p FROM Punkt p WHERE p.oeffentlich = true")
  List<Punkt> findAllByOeffentlich();

  @Transactional
  @Query("SELECT p FROM Punkt p WHERE p.oeffentlich = true OR p.nutzer.id = ?1")
  List<Punkt> findAllByOeffentlichAndNutzer(Long id);
}
