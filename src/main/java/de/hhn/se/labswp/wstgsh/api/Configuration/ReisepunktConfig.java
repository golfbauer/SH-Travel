package de.hhn.se.labswp.wstgsh.api.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.api.models.ReisepunktRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReisepunktConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  /**
   * With the help of this method you can make new entries into the database.
   * Importent: Comment out the entries you already ran.
   * If you wont do that the program will crash, since the entries already have been made.
   * @param reisepunktRepository Translates created objects into the database tables.
   * @return Arguments which will be implemented into the database.
   */
  @Bean
  CommandLineRunner commandLineRunnerReisepunkt(ReisepunktRepository reisepunktRepository) {
    return args -> {
      //Example
      //Reisepunkt test = new Reisepunkt(
      //        567L, 54.7219f, 8.494f, "Henri@gmx.de", "Föhr"
      //);
      //reisepunktRepository.saveAll(
      //        List.of(test)
      //);
    };
  }
}
