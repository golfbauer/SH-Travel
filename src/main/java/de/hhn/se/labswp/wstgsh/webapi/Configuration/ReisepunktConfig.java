package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.webapi.models.Reisepunkt;
import de.hhn.se.labswp.wstgsh.webapi.models.ReisepunktRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ReisepunktConfig {

    /**
     * Mithilfe dieser Methode können neue Einträge innerhalb der DB getätigt werden. Diese Einträge müssen, nach dem starten der Appliktion askommentiert werden.
     * @param reisepunktDAO Übermittler der die Einträge tätigt
     * @return Einträge die in die DB gehen
     */
    @Bean
    CommandLineRunner commandLineRunnerReisepunkt(ReisepunktRepository reisepunktDAO) {
        return args -> {
            /**Reisepunkt test = new Reisepunkt(
                    567L, 54.7219f, 8.494f, "Henri@gmx.de", "Föhr"
            );
            reisepunktDAO.saveAll(
                    List.of(test)
            );*/
        };
    }
}
