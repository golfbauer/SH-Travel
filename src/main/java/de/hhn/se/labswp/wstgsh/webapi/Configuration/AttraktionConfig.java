package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.Attraktion;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionOeffnungszeit;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionOeffnungszeitRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AttraktionConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  /**
   * With the help of this method you can make new entries into the database.
   * Importent: Comment out the entries you already ran.
   * If you wont do that the program will crash, since the entries already have been made.
   * @param attraktionRepository Translates created objects into the database tables.
   * @return Arguments which will be implemented into the database.
   */
  @Bean
  CommandLineRunner commandLineRunnerAttraktion(AttraktionRepository attraktionRepository,
                                                AttraktionOeffnungszeitRepository attraktionOeffnungszeitRepository) {
    return args -> {
      Attraktion attraktion = new Attraktion(34F, 34F, "testmail", "peter", "test");
      AttraktionOeffnungszeit one = new AttraktionOeffnungszeit("samstag", attraktion);
      AttraktionOeffnungszeit two = new AttraktionOeffnungszeit("sonntag", attraktion);
      attraktion.getAttraktionOeffnungszeiten().add(one);
      attraktion.getAttraktionOeffnungszeiten().add(two);
      attraktionRepository.save(attraktion);
    };
  }
}
