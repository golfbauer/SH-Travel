package de.hhn.se.labswp.wstgsh.api.models;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface NutzerRepository extends JpaRepository<Nutzer, Long> {

  Optional<Nutzer> findByEmail(String email);

  @Transactional
  @Modifying
  @Query("UPDATE Nutzer a SET a.enabled = TRUE WHERE a.email = ?1")
  int enableNutzer(String email);
}
