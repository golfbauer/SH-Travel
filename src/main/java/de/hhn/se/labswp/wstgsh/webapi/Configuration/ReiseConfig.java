package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.Reise;
import de.hhn.se.labswp.wstgsh.webapi.models.ReiseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ReiseConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  @Bean
  CommandLineRunner commandLineRunnerReise(ReiseRepository repository) {
    return args -> {
      /*List<Reise> list = new ArrayList<>();

      repository.saveAll(list);*/
    };
  }
}
