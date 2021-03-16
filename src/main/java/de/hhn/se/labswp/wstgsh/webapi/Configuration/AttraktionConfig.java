package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.webapi.models.Attraktion;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AttraktionConfig {

    /**
     * Mithilfe dieser Methode können neue Einträge innerhalb der DB getätigt werden. Diese Einträge müssen, nach dem starten der Appliktion auskommentiert werden.
     * @param attraktionRepository Übermittler der die Einträge tätigt
     * @return Einträge die in die DB gehen
     */
    @Bean
    CommandLineRunner commandLineRunnerAttraktion(AttraktionRepository attraktionRepository) {
        return args -> {
            /**Attraktion robertFeinkost = new Attraktion(
                    572L, 9.553942f, 54.52215f, "peter.lustig@haha.com", "Robert Feinkost", "Dies ist RobertsFeinkostladen", 0f
            );
            Attraktion peteAussicht = new Attraktion(
                    573L, 8.913645f, 54.469551f, "peter.lustig@haha.com", "Peters Aussicht", "Eine Wunderbare Aussicht über das Meer", 10f
            );
            attraktionRepository.saveAll(
                    List.of(robertFeinkost, peteAussicht)
            );*/
        };
    }
}
