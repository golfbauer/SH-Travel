package de.hhn.se.labswp.wstgsh.webapi.models;

import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AttraktionRepository extends JpaRepository<Attraktion, Long> {

  @Transactional
  @Query("SELECT a FROM Attraktion a WHERE a.nutzer.id = ?1")
  List<Attraktion> findAllByNutzerId(Long id);

  @Transactional
  @Query("SELECT a FROM Attraktion a WHERE a.oeffentlich = true")
  List<Attraktion> findAllByOeffentlich();

  @Transactional
  @Query("SELECT a FROM Attraktion a WHERE a.oeffentlich = true OR a.nutzer.id = ?1")
  List<Attraktion> findAllByOeffentlichAndNutzer(Long id);
}
