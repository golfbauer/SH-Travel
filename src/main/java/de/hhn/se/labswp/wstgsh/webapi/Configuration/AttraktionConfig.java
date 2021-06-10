package de.hhn.se.labswp.wstgsh.webapi.Configuration;

import de.hhn.se.labswp.wstgsh.exceptions.ReisepunktNotFoundAdvice;
import de.hhn.se.labswp.wstgsh.webapi.models.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class for configuring Attraktions.
 */
@Configuration
public class AttraktionConfig {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReisepunktNotFoundAdvice.class);

  /**
   * With the help of this method you can make new entries into the database.
   * Importent: Comment out the entries you already ran.
   * If you wont do that the program will crash, since the entries already have been made.
   *
   * @param attraktionRepository Translates created objects into the database tables.
   * @return Arguments which will be implemented into the database.
   */
  @Bean
  CommandLineRunner commandLineRunnerAttraktion(AttraktionRepository attraktionRepository,
                                                AttraktionOeffnungszeitRepository
                                                        attraktionOeffnungszeitRepository) {
    return args -> {
      List<Attraktion> list = new ArrayList<>();
      Attraktion one = new Attraktion(
              10.67966025840837f, 53.8662401645461f, "brian.didTheJob@gmx.de", "Museum Holstentor",
              "Das Lübecker Holstentor ist wohl das bekannteste Stadttor in Deutschland. Es wurde"
                      + " zwischen 1464 und 1478 von dem Lübecker Ratsbaumeister Hinrich Helmstede "
                      + "bei einer Modernisierung der Befestigungsanlagen an der Traveseite erbaut."
      );
      list.add(one);
      Attraktion two = new Attraktion(
              10.230732061508025f, 54.41217026940215f, "brian.didTheJob@gmx.de", "Marine-Ehrenmal"
              + " Laboe", "Das Lübecker Holstentor ist wohl das bekannteste Stadttor in "
              + "Deutschland. Es wurde zwischen 1464 und 1478 von dem Lübecker Ratsbaumeister "
              + "Hinrich Helmstede bei einer Modernisierung der Befestigungsanlagen an der "
              + "Traveseite erbaut."
      );
      list.add(two);
      Attraktion three = new Attraktion(
              8.941200474376009f, 54.31506447239934f, "brian.didTheJob@gmx.de", "Nationalpark"
              + "-Zentrum Multimar Wattforum", "Das Multimar Wattforum in Tönning ist das größte "
              + "Informationszentrum für den Nationalpark Schleswig-Holsteinisches Wattenmeer."
      );
      list.add(three);
      Attraktion four = new Attraktion(
              10.61980292192798f, 54.137690339032844f, "brian.didTheJob@gmx.de", "Schloss Eutin",
              "Das Eutiner Schloss in Eutin in Ostholstein bildet den kulturellen Mittelpunkt "
                      + "und die Keimzelle der Stadt und gehört neben dem Gottorfer und dem "
                      + "Glücksburger Schloss zu den bedeutendsten höfischen Profanbauten "
                      + "Schleswig-Holsteins."
      );
      list.add(four);
      Attraktion five = new Attraktion(
              11.180696086047897f, 54.445266306761326f, "brian.didTheJob@gmx.de", "Meereszentrum "
              + "Fehmarn", "Das Meereszentrum Fehmarn ist ein Schauaquarium in Burg auf der "
              + "Ostseeinsel Fehmarn."
      );
      list.add(five);
      Attraktion six = new Attraktion(
              9.049576854519923f, 54.479807115477f, "brian.didTheJob@gmx.de", "Schloss vor Husum",
              "Das Schloss vor Husum ist der einzige erhaltene "
                      + "Schlossbau an der schleswig-holsteinischen Westküste. Es dient heute als"
                      + "Schlossmuseum."
      );
      list.add(six);
      Attraktion seven = new Attraktion(
              8.876653340384884f, 54.00096497337268f, "brian.didTheJob@gmx.de", "Seehundstation "
              + "Friedrichskoog", "Erleben Sie die in Deutschland einmalige gemischte Haltung von"
              + " Seehunden und Kegelrobben! Wir freuen uns auf Ihren Besuch!"
      );
      list.add(seven);
      Attraktion eight = new Attraktion(
              9.569216743829838f, 54.49705190997663f, "brian.didTheJob@gmx.de", "Wikinger Museum "
              + "Haithabu", "Das Wikinger Museum Haithabu ist eines der bedeutendsten "
              + "archäologischen Museen Deutschlands."
      );
      list.add(eight);
      Attraktion nine = new Attraktion(
              8.297606365267928f, 54.90098700930191f, "brian.didTheJob@gmx.de", "Sylt-Aquarium",
              "Das Sylt Aquarium im Süden der Stadt Westerland auf Sylt ist ein "
                      + "zoologischer Garten, der sich auf Fische aus der Nordsee sowie tropische"
                      + " Fische aus Korallenriffen spezialisiert hat."
      );
      list.add(nine);
      Attraktion ten = new Attraktion(
              10.780061975161248f, 54.07501846712684f, "brian.didTheJob@gmx.de",
              "HANSA-PARK Freizeit- und Familienpark", "Der "
              + "Hansa-Park ist ein saisonaler Freizeitpark in "
              + "Sierksdorf an der Ostsee. Der Park umfasst heute 460.000 m² mit mehr als 125 "
              + "Attraktionen."
      );
      list.add(ten);
      attraktionRepository.saveAll(list);
    };
  }
}
