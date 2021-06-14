package de.hhn.se.labswp.wstgsh.webapi.models.service;

import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.Nutzer;
import de.hhn.se.labswp.wstgsh.webapi.models.nutzer.NutzerRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.token.BestaetigungsToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NutzerService implements UserDetailsService {

  private final NutzerRepository nutzerRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final BestaetigungsTokenService bestaetigungsTokenService;

  private final static String USER_NOT_MSG = "User with emil %s not found";

  public NutzerService(NutzerRepository nutzerRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       BestaetigungsTokenService bestaetigungsTokenService) {
    this.nutzerRepository = nutzerRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.bestaetigungsTokenService = bestaetigungsTokenService;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return nutzerRepository.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException(String.format(USER_NOT_MSG, email)));
  }

  /**
   *  Place a new Nutzer Object into the database. Gets checked if Nutzer already exists and
   *  checks if email has been copnfirmed -> send new email.
   * @param nutzer To be registered Nutzer.
   * @return Generated token.
   */
  public String signUpNutzer(Nutzer nutzer) {

    boolean nutzerExists = nutzerRepository.findByEmail(nutzer.getEmail()).isPresent();

    if (nutzerExists) {
      // TODO check if attributes are the same and
      // TODO if email not confirmed send confirmation email
      throw new IllegalStateException("Email already taken");
    }

    String encodedPasswort = bCryptPasswordEncoder.encode(nutzer.getPassword());

    nutzer.setPasswort(encodedPasswort);

    nutzerRepository.save(nutzer);

    String token = UUID.randomUUID().toString();
    BestaetigungsToken bestaetigungsToken = new BestaetigungsToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(40),
            nutzer
    );

    bestaetigungsTokenService.saveBestaetigungsToken(bestaetigungsToken);

    return token;
  }

  public int enableNutzer(String email) {
    return nutzerRepository.enableNutzer(email);
  }
}
