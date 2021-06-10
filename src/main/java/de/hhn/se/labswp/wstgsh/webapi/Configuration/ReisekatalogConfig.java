package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.Reise;
import de.hhn.se.labswp.wstgsh.webapi.models.Reisekatalog;
import de.hhn.se.labswp.wstgsh.webapi.models.ReisekatalogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ReisekatalogConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  @Bean
  CommandLineRunner commandLineRunnerReisekatalog(ReisekatalogRepository repository) {
    return args -> {
      List<Reisekatalog> list = new ArrayList<>();

      repository.saveAll(list);
    };
  }
}
