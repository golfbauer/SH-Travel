package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReiseRepository extends JpaRepository<Reise, Long> {

  @Transactional
  @Query("SELECT r FROM Reise r WHERE r.oeffentlich = true AND r.nutzer = ?1")
  List<Reise> findAllByOeffentlichAndNutzer(Nutzer nutzer);

  @Transactional
  @Query("SELECT r FROM Reise r WHERE r.oeffentlich = true")
  List<Reise> findAllByOeffentlich();
}
