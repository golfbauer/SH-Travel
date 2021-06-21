package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReisepunktRepository extends JpaRepository<Reisepunkt, Long> {

  @Transactional
  @Query("select reisepunkte FROM Reisepunkt reisepunkte WHERE reisepunkte.oeffentlich = true")
  List<Reisepunkt> findAllByOeffentlich();

  @Transactional
  @Query("SELECT r FROM Reisepunkt r WHERE r.oeffentlich = true OR r.nutzer = ?1")
  List<Reisepunkt> findAllByOeffentlichAndNutzer(Nutzer nutzer);
}
