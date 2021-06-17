package de.hhn.se.labswp.wstgsh.webapi.models.service;

import de.hhn.se.labswp.wstgsh.webapi.models.token.BestaetigungsToken;
import de.hhn.se.labswp.wstgsh.webapi.models.token.BestaetigungsTokenRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BestaetigungsTokenService {

  private final BestaetigungsTokenRepository bestaetigungsTokenRepository;

  public BestaetigungsTokenService(BestaetigungsTokenRepository bestaetigungsTokenRepository) {
    this.bestaetigungsTokenRepository = bestaetigungsTokenRepository;
  }

  /**
   * Save Token into the Database.
   * @param token BestaetigungsToken to be saved.
   */
  public void saveBestaetigungsToken(BestaetigungsToken token) {
    bestaetigungsTokenRepository.save(token);
  }

  /**
   * Return BestaetigungsToken via token.
   * @param token String which represents the token.
   * @return BestaetigungsToken
   */
  public Optional<BestaetigungsToken> getToken(String token) {
    return bestaetigungsTokenRepository.findByToken(token);
  }

  /**
   * Set time for confirmation of token.
   * @param token String which represents the token.
   * @return Integer for true and false.
   */
  public int setConfirmedAt(String token) {
    return bestaetigungsTokenRepository.updateConfirmedAt(
            token, LocalDateTime.now()
    );
  }
}
