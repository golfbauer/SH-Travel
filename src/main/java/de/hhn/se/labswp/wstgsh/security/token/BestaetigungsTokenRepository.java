package de.hhn.se.labswp.wstgsh.security.token;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BestaetigungsTokenRepository extends JpaRepository<BestaetigungsToken, Long> {

  Optional<BestaetigungsToken> findByToken(String token);

  @Transactional
  @Modifying
  @Query("UPDATE BestaetigungsToken c SET c.beastaetigtUm = ?2 WHERE c.token = ?1")
  int updateConfirmedAt(String token, LocalDateTime beastaetigtUm);
}
