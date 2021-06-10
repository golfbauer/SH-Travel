package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.Punkt;
import de.hhn.se.labswp.wstgsh.webapi.models.PunktRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class PunktConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  /**
   * With the help of this method you can make new entries into the database.
   * Importent: Comment out the entries you already ran.
   * If you wont do that the program will crash, since the entries already have been made.
   * @param punktRepository Translates created objects into the database tables.
   * @return Arguments which will be implemented into the database.
   */
  @Bean
  CommandLineRunner commandLineRunnerPunkt(PunktRepository punktRepository) {
    return args -> {
      /*List<Punkt> list = new ArrayList<>();
      Punkt omaBeate = new Punkt(
              10.692964196205141f, 53.867987608631005f,
              "Henri@gmx.de", "Oma Beate"
      );
      Punkt opaKarlHeins = new Punkt(
              9.990327358245851f, 54.06959447701732f,
              "Henri@gmx.de", "Omp Karl Heins"
      );
      Punkt zwischenStop = new Punkt(
              9.605441093444826f, 54.420602956508525f,
              "Brian@gmx.de", "Halbe Strecke Rast"
      );
      Punkt tinglevUmstieg = new Punkt(
              9.258213043212892f, 54.93557467072232f,
              "Brian@gmx.de", "Umstieg an Tinglev"
      );
      Punkt padborgAusstieg = new Punkt(
              9.605441093444826f, 54.420602956508525f,
              "Brian@gmx.de", "Ausstieg an Padborg"
      );
      Punkt lieblingsstrand = new Punkt(
              8.62804412841797f, 54.89788457498233f,
              "Ben@gmx.de", "Mein Lieblingsstrand"
      );
      Punkt grenzeNachDaenemark = new Punkt(
              9.330139160156252f, 54.80068486732236f,
              "Ben@gmx.de", "Grenze nach Dänemark"
      );
      Punkt stelleZumFischen = new Punkt(
              10.496642589569094f, 54.387630018013894f,
              "Dennis@gmx.de", "Die perfekte Stelle zum Fischen"
      );
      Punkt schlachtkreuzer = new Punkt(
              10.208702087402346f, 54.395351059646764f,
              "Dennis@gmx.de", "Ein beendruckender Schlachtkreuzer"
      );
      Punkt meinEigenesBoot = new Punkt(
              10.021140575408937f, 54.6625385923639f,
              "Pascal@gmx.de", "Mein Eigenes Boot"
      );
      list.add(omaBeate);
      list.add(opaKarlHeins);
      list.add(zwischenStop);
      list.add(tinglevUmstieg);
      list.add(padborgAusstieg);
      list.add(lieblingsstrand);
      list.add(grenzeNachDaenemark);
      list.add(stelleZumFischen);
      list.add(schlachtkreuzer);
      list.add(meinEigenesBoot);
      punktRepository.saveAll(list);*/
    };
  }
}
