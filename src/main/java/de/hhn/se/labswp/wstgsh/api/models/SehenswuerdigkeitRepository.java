package de.hhn.se.labswp.wstgsh.api.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SehenswuerdigkeitRepository extends JpaRepository<Sehenswuerdigkeit, Long> {

  @Transactional
  @Query("SELECT a FROM Sehenswuerdigkeit a WHERE a.nutzer.id = ?1")
  List<Sehenswuerdigkeit> findAllByNutzerId(Long id);

  @Transactional
  @Query("SELECT a FROM Sehenswuerdigkeit a WHERE a.oeffentlich = true")
  List<Sehenswuerdigkeit> findAllByOeffentlich();

  @Transactional
  @Query("SELECT a FROM Sehenswuerdigkeit a WHERE a.oeffentlich = true OR a.nutzer.id = ?1")
  List<Sehenswuerdigkeit> findAllByOeffentlichAndNutzer(Long id);
}
