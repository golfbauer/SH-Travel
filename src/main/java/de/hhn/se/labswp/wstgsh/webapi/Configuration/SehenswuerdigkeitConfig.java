package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.webapi.models.Sehenswuerdigkeit;
import de.hhn.se.labswp.wstgsh.webapi.models.SehenswuerdigkeitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SehenswuerdigkeitConfig {

    /**
     * Mithilfe dieser Methode können neue Einträge innerhalb der DB getätigt werden. Diese Einträge müssen, nach dem starten der Appliktion askommentiert werden.
     * @param sehenswuerdigkeitRepository Übermittler der die Einträge tätigt
     * @return Einträge die in die DB gehen
     */
    @Bean
    CommandLineRunner commandLineRunnerSehenswuerdigkeit(SehenswuerdigkeitRepository sehenswuerdigkeitRepository) {
        return args -> {
            /**Sehenswuerdigkeit foehr = new Sehenswuerdigkeit(
                    567L, 8.494f, 54.7219f, "Henri@gmx.de", "Föhr", "Eine wunderschöne Insel im Naturschutzgebiet Nordfriesisches Meer"
            );
            Sehenswuerdigkeit fehmarn = new Sehenswuerdigkeit(
                    568L, 11.1346f, 54.4739f, "Henri@gmx.de", "Fehmarn", "Eine Insel ion der Nähe zu Dänemark"
            );
            sehenswuerdigkeitRepository.saveAll(
                    List.of(foehr, fehmarn)
            );*/
        };
    }
}
