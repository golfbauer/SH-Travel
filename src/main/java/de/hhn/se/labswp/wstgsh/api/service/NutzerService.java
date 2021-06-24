package de.hhn.se.labswp.wstgsh.api.service;

import de.hhn.se.labswp.wstgsh.api.models.Nutzer;
import de.hhn.se.labswp.wstgsh.api.models.NutzerRepository;
import de.hhn.se.labswp.wstgsh.security.registration.RegistrationAntwort;
import de.hhn.se.labswp.wstgsh.security.token.BestaetigungsToken;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NutzerService implements UserDetailsService {

  private final NutzerRepository nutzerRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final BestaetigungsTokenService bestaetigungsTokenService;

  private final static String USER_NOT_MSG = "User with emil %s not found";

  /**
   * Constructor for NutzerService.
   * @param nutzerRepository Repo for database requests for Nutzer.
   * @param bCryptPasswordEncoder To encrypt the password of a user.
   * @param bestaetigungsTokenService Repo for database request for Token.
   */
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
  public RegistrationAntwort signUpNutzer(Nutzer nutzer) {

    RegistrationAntwort antwort = new RegistrationAntwort();

    boolean nutzerExists = nutzerRepository.findByEmail(nutzer.getEmail()).isPresent();

    if (nutzerExists) {
      nutzerRepository.findByEmail(nutzer.getUsername()).map(
              existingNutzer -> {
                if (existingNutzer.isEnabled()) {
                  throw new IllegalStateException("Email ist bereits vergeben und Nutzer ist "
                          + "bestätigt.");
                } else {
                  throw new IllegalStateException("Nutzer hat bereits einen Account, diesen aber "
                          + "nicht bestätigt");
                }
              });
    } else {
      antwort.setAntwort("Nutzer hat noch keinen Account.");

      String encodedPasswort = bCryptPasswordEncoder.encode(nutzer.getPassword());

      nutzer.setPasswort(encodedPasswort);

      nutzerRepository.save(nutzer);

      String token = createToken(nutzer);

      antwort.setToken(token);
    }
    return antwort;
  }

  /**
   * Resends Token on request by Nutzer.
   * @param email Email of Nutzer.
   * @return Response containing Token and an answer.
   */
  public RegistrationAntwort resendToken(String email) {
    RegistrationAntwort antwort = new RegistrationAntwort();
    boolean nutzerExists = nutzerRepository.findByEmail(email).isPresent();
    if (nutzerExists) {
      nutzerRepository.findByEmail(email).map(nutzer -> {
        if (!nutzer.isEnabled()) {
          String token = createToken(nutzer);
          antwort.setToken(token);
          antwort.setAntwort("Token wurde erneut gesendet.");
        } else {
          throw new IllegalStateException("Nutzer ist bereits bestätigt.");
        }
        return nutzer;
      });
    } else {
      throw new IllegalStateException("Nutzer mit Email existiert noch nicht.");
    }
    return antwort;
  }

  /**
   * Creates Token and saves it to the DB.
   * @param nutzer Nutzer who owns the Token.
   * @return Returning created Token.
   */
  public String createToken(Nutzer nutzer) {
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

  /**
   * Enable User.
   * @param email Email of Nutzer.
   * @return Success or Failure.
   */
  public int enableNutzer(String email) {
    return nutzerRepository.enableNutzer(email);
  }
}
