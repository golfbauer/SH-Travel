package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.webapi.models.Attraktion;
import de.hhn.se.labswp.wstgsh.webapi.models.AttraktionRepository;
import de.hhn.se.labswp.wstgsh.webapi.models.Punkt;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PunktConfig {

    /**
     * Mithilfe dieser Methode können neue Einträge innerhalb der DB getätigt werden. Diese Einträge müssen, nach dem starten der Appliktion askommentiert werden.
     * @param punktRepository Übermittler der die Einträge tätigt
     * @return Einträge die in die DB gehen
     */
    @Bean
    CommandLineRunner commandLineRunnerPunkt(PunktRepository punktRepository) {
        return args -> {
            /**Punkt oma = new Punkt(
                    570L, 9.992022f, 54.069736f, "Henri@gmx.de", "Oma Beate"
            );

            Punkt ingriet = new Punkt(
                    571L, 10.120425f, 54.328009f, "Henri@gmx.de", "Tante Ingriet"
            );
            punktRepository.saveAll(
                    List.of(oma, ingriet)
            );*/
        };
    }
}
