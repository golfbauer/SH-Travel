package de.hhn.se.labswp.wstgsh.api.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.api.models.ReisekatalogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReisekatalogConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  @Bean
  CommandLineRunner commandLineRunnerReisekatalog(ReisekatalogRepository repository) {
    return args -> {
      /*List<Reisekatalog> list = new ArrayList<>();

      repository.saveAll(list);*/
    };
  }
}
