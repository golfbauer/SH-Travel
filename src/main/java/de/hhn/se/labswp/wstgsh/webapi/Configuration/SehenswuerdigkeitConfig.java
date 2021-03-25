package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.SehenswuerdigkeitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SehenswuerdigkeitConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  /**
   * With the help of this method you can make new entries into the database.
   * Importent: Comment out the entries you already ran.
   * If you wont do that the program will crash, since the entries already have been made.
   * @param sehenswuerdigkeitRepository Translates created objects into the database tables.
   * @return Arguments which will be implemented into the database.
   */
  @Bean
  CommandLineRunner commandLineRunnerSehenswuerdigkeit(SehenswuerdigkeitRepository
                                                               sehenswuerdigkeitRepository) {
    return args -> {
       //Example
       //Sehenswuerdigkeit fehmarn = new Sehenswuerdigkeit(
       //568L, 11.1346f, 54.4739f, "Henri@gmx.de", "Fehmarn", "Eine Insel ion der Nähe zu Dänemark"
       //);
       //sehenswuerdigkeitRepository.saveAll(
       //List.of(foehr, fehmarn)
       //);
    };
  }
}
