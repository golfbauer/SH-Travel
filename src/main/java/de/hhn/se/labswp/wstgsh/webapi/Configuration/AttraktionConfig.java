package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
  CommandLineRunner commandLineRunnerAttraktion(AttraktionRepository attraktionRepository) {
    return args -> {
      //Example
      //Attraktion robertFeinkost = new Attraktion(
      // 572L, 9.553942f, 54.52215f, "peter.lustig@haha.com", "Robert Feinkost",
      // "Dies ist RobertsFeinkostladen", 0f
      // );
      // attraktionRepository.saveAll(
      // List.of(robertFeinkost, peteAussicht)
      // );
    };
  }
}
